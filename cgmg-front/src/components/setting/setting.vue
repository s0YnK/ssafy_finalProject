<template>
    <div class="update-form">
        <form action="" class="form" @submit.prevent="onsubmit">
            <h1 class="form__title">수정페이지</h1>

            <div class="update-div">
                <input type="text" class="update-input" placeholder=" " v-model="data.userId">
                <label class="update-label">ID (변경불가)</label>
            </div>
            <div class="update-div">
                <input type="password" class="update-input" placeholder=" " v-model="data.password">
                <label class="update-label">Password</label>
            </div>

            <div class="update-div">
                <input type="email" class="update-input" placeholder=" " v-model="data.email">
                <label class="update-label">이메일</label>
            </div>
            <div class="update-div">
                <input type="text" class="update-input" placeholder=" " v-model="data.name">
                <label class="update-label">이름 (변경불가)</label>
            </div>
            <div class="update-div">
                <input type="text" class="update-input" placeholder=" " v-model="data.nickName">
                <label class="update-label">닉네임</label>
            </div>
            <div class="update-div">
                <input type="text" class="update-input" placeholder=" " v-model="data.profileImg">
                <label class="update-label">프로필이미지</label>
            </div>
            <input type="submit" class="update-button" value="update" @click="update">




        </form>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore()

const user = JSON.parse(localStorage.getItem("loginUser"));
const data = ref({
    userId: user.userId,
    name: user.name,
    password: user.password,
    email: user.email,
    nickName: user.nickName,
    profileImg: user.profileImg,
})


const update = function () {
    console.log(data.value)
    store.updateUser(data.value)
}
</script>

<style scoped>
.update-form {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50vh;
}

.form {
    width: 360px;
    padding: 3rem 2rem;
    border-radius: 1rem;
    /* box-shadow: 0 10px 25px rgba(92, 99, 105, 0.2); */
}

.form__title {
    font-weight: 500;
    margin-bottom: 2.5rem;
}

.update-div {
    position: relative;
    height: 52px;
    margin-bottom: 1.5rem;
}

.update-input {
    position: absolute;
    top: 0;
    left: 0;
    width: 90%;
    height: 50%;
    font-size: 1rem;
    border: 2px solid #DADCE0;
    border-radius: 0.5rem;
    outline: none;
    padding: 1rem;
    background: none;
    z-index: 1;
}

.update-label {
    position: absolute;
    left: 1rem;
    top: 1.3rem;
    padding: 0 0.25rem;
    background-color: #fff;
    color: #80868B;
    font-size: 1rem;
    transition: 0.3s;
}

.update-button {
    display: block;
    margin-left: auto;
    padding: 0.75rem 2rem;
    outline: none;
    border: none;
    background-color: rgb(184, 225, 211);
    color: #fff;
    font-size: 1rem;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
}

.update-button:hover {
    background-color: rgb(55, 182, 140);
}

.update-input:focus+.update-label {
    top: -0.3rem;
    left: 0.8rem;
    color: rgb(55, 182, 140);
    font-size: .75rem;
    font-weight: 500;
    z-index: 10;
}

.update-input:not(:placeholder-shown).update-input:not(:focus)+.update-label {
    top: -0.3rem;
    left: 0.8rem;
    z-index: 10;
    font-size: .75rem;
    font-weight: 500;
}

.update-input:focus {
    border: 2px solid rgb(55, 182, 140)
}
</style>