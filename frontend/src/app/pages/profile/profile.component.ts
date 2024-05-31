import { Component } from '@angular/core';
import { ToolbarComponent } from '../../shared/components/toolbar/toolbar.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [ToolbarComponent, MatTabsModule, MatButtonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  username = '@kingjames';
  bio =
    'Desenvolvedor de software com paixão por resolver problemas complexos e criar soluções inovadoras. Amante de tecnologia, música e viagens.';
}
