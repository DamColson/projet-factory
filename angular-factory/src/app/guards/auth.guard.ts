import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  let role = JSON.parse(localStorage.getItem('compte')!).role;
  if (role != null) {
    return true;
  }
  return inject(Router).createUrlTree(['/login']);
};
