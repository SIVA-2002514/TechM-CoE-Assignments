import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  @Output() registrationSuccess = new EventEmitter<void>();

  user = {
    name: '',
    email: ''
  };

  showError = false;
  registered = false;

  onRegister() {
    // Check if both fields are filled
    if (!this.user.name || !this.user.email) {
      this.showError = true;
      this.registered = false; // Ensure success message doesn't show when form is invalid
      return;
    }

    this.showError = false;
    this.registered = true;  // ✅ Set to true after successful registration
    this.registrationSuccess.emit();
  }
}
