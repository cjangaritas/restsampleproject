import { Component, OnInit, signal, computed, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { FestivityService } from './services/festivity.service';
import { Festivity } from './models/festivity';

@Component({
  selector: 'app-root',
  imports: [FormsModule, DatePipe],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  private readonly festivityService = inject(FestivityService);

  // States
  protected readonly festivities = signal<Festivity[]>([]);
  protected readonly isLoading = signal<boolean>(true);
  protected readonly errorMessage = signal<string | null>(null);
  protected readonly successMessage = signal<string | null>(null);

  // Filters
  protected readonly searchQuery = signal<string>('');
  protected readonly selectedLocation = signal<string>('');

  // Drawer / Form State
  protected readonly isFormOpen = signal<boolean>(false);
  protected readonly formMode = signal<'create' | 'edit'>('create');
  
  // Current editing form values
  protected readonly formId = signal<string | undefined>(undefined);
  protected readonly formName = signal<string>('');
  protected readonly formStartDate = signal<string>('');
  protected readonly formEndDate = signal<string>('');
  protected readonly formLocationName = signal<string>('');

  ngOnInit() {
    this.loadFestivities();
  }

  // Load from backend
  protected loadFestivities() {
    this.isLoading.set(true);
    this.errorMessage.set(null);
    this.festivityService.getAll().subscribe({
      next: (data) => {
        this.festivities.set(data || []);
        this.isLoading.set(false);
      },
      error: (err) => {
        // The API returns 404 if no festivities are found
        if (err.status === 404) {
          this.festivities.set([]);
        } else {
          this.errorMessage.set('Failed to connect to the backend server. Please verify MongoDB and the Spring Boot application are running.');
        }
        this.isLoading.set(false);
      }
    });
  }

  // Computed Values
  protected readonly filteredFestivities = computed(() => {
    const query = this.searchQuery().toLowerCase().trim();
    const location = this.selectedLocation();
    
    return this.festivities().filter(f => {
      const matchesSearch = !query || 
        f.name.toLowerCase().includes(query) || 
        (f.locationName && f.locationName.toLowerCase().includes(query));
      
      const matchesLocation = !location || f.locationName === location;
      
      return matchesSearch && matchesLocation;
    });
  });

  protected readonly uniqueLocations = computed(() => {
    const locations = this.festivities()
      .map(f => f.locationName)
      .filter((loc): loc is string => !!loc);
    return Array.from(new Set(locations)).sort();
  });

  // Statistics
  protected readonly stats = computed(() => {
    const list = this.festivities();
    const today = new Date().toISOString().split('T')[0];
    
    let upcoming = 0;
    let ongoing = 0;
    let completed = 0;

    list.forEach(f => {
      if (f.startDate > today) {
        upcoming++;
      } else if (f.endDate < today) {
        completed++;
      } else {
        ongoing++;
      }
    });

    return {
      total: list.length,
      upcoming,
      ongoing,
      completed
    };
  });

  // Form Management
  protected openCreateDrawer() {
    this.formMode.set('create');
    this.formId.set(undefined);
    this.formName.set('');
    this.formStartDate.set('');
    this.formEndDate.set('');
    this.formLocationName.set('');
    this.errorMessage.set(null);
    this.isFormOpen.set(true);
  }

  protected openEditDrawer(festivity: Festivity) {
    this.formMode.set('edit');
    this.formId.set(festivity.id);
    this.formName.set(festivity.name);
    this.formStartDate.set(festivity.startDate);
    this.formEndDate.set(festivity.endDate);
    this.formLocationName.set(festivity.locationName || '');
    this.errorMessage.set(null);
    this.isFormOpen.set(true);
  }

  protected closeDrawer() {
    this.isFormOpen.set(false);
    this.errorMessage.set(null);
  }

  protected saveFestivity() {
    this.errorMessage.set(null);
    this.successMessage.set(null);

    // Validation
    const name = this.formName().trim();
    const start = this.formStartDate();
    const end = this.formEndDate();
    const loc = this.formLocationName().trim();

    if (!name) {
      this.errorMessage.set('Festivity Name is required.');
      return;
    }
    if (!start || !end) {
      this.errorMessage.set('Both Start and End Dates are required.');
      return;
    }
    if (new Date(start) > new Date(end)) {
      this.errorMessage.set('Start Date must be before or equal to End Date.');
      return;
    }

    const payload: Festivity = {
      id: this.formId(),
      name,
      startDate: start,
      endDate: end,
      locationName: loc || undefined
    };

    const request$ = this.formMode() === 'create' 
      ? this.festivityService.create(payload)
      : this.festivityService.update(payload);

    request$.subscribe({
      next: () => {
        this.successMessage.set(
          this.formMode() === 'create' 
            ? `Successfully created "${name}"!` 
            : `Successfully updated "${name}"!`
        );
        this.closeDrawer();
        this.loadFestivities();
        setTimeout(() => this.successMessage.set(null), 5000);
      },
      error: (err) => {
        console.error(err);
        if (err.error && err.error.message) {
          this.errorMessage.set(err.error.message);
        } else if (err.status === 400) {
          this.errorMessage.set('Invalid input parameters. Please verify the dates and name uniqueness.');
        } else {
          this.errorMessage.set('Failed to save festivity. Make sure name is unique and backend is operational.');
        }
      }
    });
  }
}
