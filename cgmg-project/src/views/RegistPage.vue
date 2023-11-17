<template>
    <div class="regist-form">
        <form action="" class="form" @submit.prevent="onsubmit">
            <h1 class="form__title">regist</h1>

            <div class="regist-div">
                <input type="text" class="regist-input" placeholder=" " v-model="userId">
                <label class="regist-label">ID</label>
            </div>
            <div class="regist-div">
                <input type="password" class="regist-input" placeholder=" " v-model="password">
                <label class="regist-label">Password</label>
            </div>

            <div class="regist-div">
                <input type="email" class="regist-input" placeholder=" " v-model="email">
                <label class="regist-label">이메일</label>
            </div>
            <div class="regist-div">
                <input type="text" class="regist-input" placeholder=" " v-model="name">
                <label class="regist-label">이름</label>
            </div>
            <div class="regist-div">
                <input type="text" class="regist-input" placeholder=" " v-model="nickName">
                <label class="regist-label">닉네임</label>
            </div>
            <div class="regist-div">
                <input type="text" class="regist-input" placeholder=" " v-model="profileImg">
                <label class="regist-label">프로필이미지</label>
            </div>
            <input type="submit" class="regist-button" value="regist" @click="regist">
        </form>
    </div>
</template>

<script setup>
import { ref } from "vue";

const emit = defineEmits(["create-user"]);

const userId = ref("");
const password = ref("");
const name = ref("");
const email = ref("");
const nickName = ref("");
const profileImg = ref("");


const regist = () => {
    if (
        userId.value === "" ||
        password.value === "" ||
        email.value === "" ||
        nickName.value === "" ||
        profileImg.value === "" ||
        name.value === ""
    ) {
        alert("모든 내용을 입력해주세요");
        return;
    }

    const createUser = {
        userId: userId.value,
        password: password.value,
        name: name.value,
        email: email.value,
        nickName: nickName.value,
        profileImg: profileImg.value,
    };

    emit("create-user", createUser);
};

</script>

<style scoped>
.regist-form {
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

.regist-div {
    position: relative;
    height: 52px;
    margin-bottom: 1.5rem;
}

.regist-input {
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

.regist-label {
    position: absolute;
    left: 1rem;
    top: 1.3rem;
    padding: 0 0.25rem;
    background-color: #fff;
    color: #80868B;
    font-size: 1rem;
    transition: 0.3s;
}

.regist-button {
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

.regist-button:hover {
    background-color: rgb(55, 182, 140);
}

.regist-input:focus+.regist-label {
    top: -0.3rem;
    left: 0.8rem;
    color: rgb(55, 182, 140);
    font-size: .75rem;
    font-weight: 500;
    z-index: 10;
}

.regist-input:not(:placeholder-shown).regist-input:not(:focus)+.regist-label {
    top: -0.3rem;
    left: 0.8rem;
    z-index: 10;
    font-size: .75rem;
    font-weight: 500;
}

.regist-input:focus {
    border: 2px solid rgb(55, 182, 140)
}
</style>