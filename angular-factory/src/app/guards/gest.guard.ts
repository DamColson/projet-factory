import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const gestGuard: CanActivateFn = (route, state) => {
  let role = JSON.parse(localStorage.getItem('compte')!).role;
  if (role == 'ROLE_GESTIONNAIRE' || role == 'ROLE_ADMIN') {
    return true;
  }
  return inject(Router).createUrlTree(['/login']);
};
