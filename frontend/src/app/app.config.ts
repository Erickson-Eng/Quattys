import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';


export function tokenGetter() {
  return localStorage.getItem('quattysToken');
}

export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(JwtModule.forRoot({
      config:{
        tokenGetter: tokenGetter,
        disallowedRoutes: ['/auth']
      }
    })),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(),
    provideAnimationsAsync(),
    provideAnimationsAsync(),
  ],
};
