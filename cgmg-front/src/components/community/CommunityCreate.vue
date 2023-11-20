<template>
    <div class="container">
        <h2>리뷰 작성</h2>

        <div class="form__div">
            <div class="lain tit">
                <label for="title" class="form__label">제목</label>
                <input type="text" id="title" v-model="board.title" class="form__input input">
            </div>
            <div class="lain">
                <label for="gymAddress" class="form__label">장소 선택</label>
                <input type="text" id="gymAddress" v-model="board.gymAddress" class="form__input input">
            </div>
            <div class="lain">
                <label for="content" class="form__label">내용</label>
                <textarea id="content" cols="30" rows="10" v-model="board.content" class="form__input textarea"></textarea>
            </div>
            <div class="buttonarea">
                <button @click="createBoard" class="search-btn">등록</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/community";
import { useRoute } from "vue-router";

const store = useBoardStore()
const board = ref({
    writer: JSON.parse(localStorage.getItem("loginUser")).userId,
    title: '',
    content: '',
    gymAddress: '',
})

onMounted(() => {
    const route = useRoute()
});
const route = useRoute();

const createBoard = function () {
    store.createBoard(board.value)
}
</script>

<style scoped>
.container {
    max-width: 800px;
    margin: 20px auto;
    background-color: #fff;
    border-radius: 8px;
    padding: 20px;
}

.form-group {
    margin-bottom: 20px;
}

.lain {
    display: flex;
}

label {
    width: 40px;
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
    margin-right: 20px;
    font-size: 20px;
}

.input {
    font-size: 16px;
    margin-bottom: 40px;
    width: 90%;
    padding: 10px;
    box-sizing: border-box;
    border: none;
    border-bottom: 1px solid #ccc;
    border-radius: 0;
    transition: border-bottom 0.3s;
}

.tit {
    margin-top: 40px;
}

.textarea {
    width: 90%;
    padding: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 10px;
    margin-bottom: 40px;
}

.input:focus {
    outline: none;
    border-bottom: 1px solid #6ed8b7;
}

.textarea:focus {
    outline: none;
    border: 1px solid #6ed8b7;
}

.buttonarea {
    width: 800px;
    display: flex;
    justify-content: center;
}

.search-btn {
    font-size: 20px;
    color: #fff;
    width: 120px;
    height: 50px;
    border-radius: 10px;
    border: solid 2px rgb(184, 225, 211);
    background-color: rgb(184, 225, 211);
    transition: 0.2s;
    cursor: pointer;
}

.search-btn:hover {
    background-color: rgb(55, 182, 140);
    border: solid 2px rgb(55, 182, 140);
}

.search-btn:click {
    background-color: rgb(184, 225, 211);
}
</style>