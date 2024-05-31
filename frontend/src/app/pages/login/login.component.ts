import { CreateUserDTO } from './../../shared/models/createUserDTO';
import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import { AuthService } from '../../core/services/auth/auth.service';
import { Subject, takeUntil } from 'rxjs';
import { UserService } from '../../core/services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    MatDividerModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit, OnDestroy {
  private readonly destroy$: Subject<void> = new Subject();
  loginForm!: FormGroup;
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initializeLoginForm();
    this.initializeRegisterForm();
  }

  initializeLoginForm() {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: ['', [Validators.required]],
    });
  }

  initializeRegisterForm() {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(5)]],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
          ),
        ],
      ],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.pattern(/^\+?[0-9. ()-]{7,25}$/)]],
    });
  }

  submitLogin() {
    this.authService
      .login(
        this.loginForm.get('username')?.value,
        this.loginForm.get('password')?.value
      )
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (resp) => {
          this.authService.setToken(resp.body.token);
          this.router.navigate(['/home']);
        },
        error: () => {
          this.snackBar.open('Erro nas credenciais', 'Fechar', {
            duration: 3000,
          });
        },
      });
  }

  submitRegister() {
    if (this.registerForm.valid) {
      const formValues: CreateUserDTO = this.registerForm
        .value as CreateUserDTO;
      this.userService
        .createUser(formValues)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (resp) => {
            if (resp.status === 201) {
              this.loginForm.get('username')?.setValue(formValues.username);
              this.loginForm.get('password')?.setValue(formValues.password);
              this.submitLogin();
            }
          },
        });
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
