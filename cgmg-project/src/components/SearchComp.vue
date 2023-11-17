<template>
    <div>
        <div class="search-container" :class="{ 'expanded': isExpanded || searchTerm !== '' }">
            <input type="text" class="search-box" v-model="searchTerm" @keyup.enter="search" @focus="expandSearch"
                @blur="collapseSearch" />
            <button class="search-icon" @click="toggleSearch"> <i class="fas fa-search"></i></button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const searchTerm = ref('');
const isExpanded = ref(false);

function search() {
    alert('검색어: ' + searchTerm.value);
}

function expandSearch() {
    isExpanded.value = true;
}

function collapseSearch() {
    isExpanded.value = false;
}

function toggleSearch() {
    isExpanded.value = !isExpanded.value;
}
</script>

<style scoped>
:root {
    --primary-100: #424874;
    --primary-200: #A6B1E1;
    --primary-300: #fdf6fd;
    --accent-100: #D9ACF5;
    --accent-200: #FFCEFE;
    --text-100: #292524;
    --text-200: #64748b;
    --bg-100: #ffffff;
    --bg-200: #f5f5f5;
    --bg-300: #cccccc;
}

.search-container {
    position: relative;
}

.search-box {
    color: var(--text-100);
    width: 70px;
    padding: 10px;
    border: 2px solid var(--primary-100);
    border-radius: 25px;
    outline: none;
    background-color: rgba(255, 255, 255, 0.0);
    transition: width 0.3s, background-color 0.3s;
}

.expanded .search-box {
    width: 150px;
    background-color: var(--bg-100);
}

.search-icon {
    color: var(--text-100);
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    background: none;
    border: none;
    cursor: pointer;
    outline: none;
}
</style>
