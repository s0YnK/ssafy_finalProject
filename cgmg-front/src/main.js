import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
document.documentElement.setAttribute('color-theme', 'light');
const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

const $checkbox = document.querySelector('.check');

$checkbox.addEventListener('click', e => {
  if (e.target.checked) {
    document.documentElement.setAttribute('color-theme', 'dark');
  } else {
    document.documentElement.setAttribute('color-theme', 'light');
  }
});