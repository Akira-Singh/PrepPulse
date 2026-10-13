import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard';

export const routes: Routes = [
    {
        path: '', 
        loadComponent: () =>import('./components/dashboard/dashboard').then(m => m.DashboardComponent)
    },
    {
        path: 'analytics',
        loadComponent: () => import('./components/analytics/analytics').then(m => m.AnalyticsComponent)
    }
];
