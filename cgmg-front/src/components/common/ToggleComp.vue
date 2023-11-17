<template>
    <div>
        <label class="switch">
            <input v-model="darkMode" class="check" type="checkbox" id="darkModeToggle" />
            <span class="slider" :style="{ backgroundColor: darkMode ? '#292e3b' : '#f5f5f5' }"></span>
        </label>
    </div>
</template>

<script setup>
import { ref, watchEffect } from 'vue';

const darkMode = ref(false);

const toggleDarkMode = () => {
    document.documentElement.setAttribute('color-theme', darkMode.value ? 'dark' : 'light');
};

darkMode.value = localStorage.getItem('darkMode') === 'true';

toggleDarkMode(); // 초기 설정 적용

const updateLocalStorage = () => {
    localStorage.setItem('darkMode', darkMode.value.toString());
};

// darkMode 변경 시 실행되는 함수 등록
watchEffect(() => {
    toggleDarkMode();
    updateLocalStorage();
});

</script>

<style scoped>
.switch {
    position: relative;
    display: inline-block;
    width: 70px;
    height: 40px;
}

.switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

.slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    transition: 0.4s;
    border-radius: 34px;
    border: solid 2px var(--primary-100);
}

.slider:before {
    content: "";
    position: absolute;
    height: 26px;
    width: 26px;
    right: 5px;
    bottom: 5px;
    background-color: var(--sun);
    transition: 0.4s;
    border-radius: 50%;
    clip-path: polygon(0 0, 0 100%, 100% 100%, 100% 0, 50% 0);
}

input:checked+.slider:before {
    transform: translateX(-29px);
    clip-path: polygon(0 0, 0 100%, 100% 100%, 100% 0, 0 0);
}

/* input:focus+.slider {
    box-shadow: 0 0 1px #2196F3;
} */
</style>
