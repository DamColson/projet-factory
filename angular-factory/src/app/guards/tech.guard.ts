import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const techGuard: CanActivateFn = (route, state) => {
  let role = JSON.parse(localStorage.getItem('compte')!).role;
  if (role == 'ROLE_TECHNICIEN' || role == 'ROLE_ADMIN') {
    return true;
  }
  return inject(Router).createUrlTree(['/login']);
};
