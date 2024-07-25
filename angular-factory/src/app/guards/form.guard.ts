import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const formGuard: CanActivateFn = (route, state) => {
  let role = JSON.parse(localStorage.getItem('compte')!).role;
  if (role == 'ROLE_FORMATEUR') {
    return true;
  }
  return inject(Router).createUrlTree(['/login']);
};
