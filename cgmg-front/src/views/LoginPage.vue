<template>
    <div class="login-form">
        <form action="" class="form" @submit.prevent="onsubmit">
            <h1 class="form__title">로그인</h1>

            <div class="login-div">
                <input type="text" class="login-input" placeholder=" " v-model="userId">
                <label for="" class="login-label">ID</label>
            </div>

            <div class="login-div">
                <input type="password" class="login-input" placeholder=" " v-model="password">
                <label for="" class="login-label">비밀번호</label>
            </div>

            <input type="submit" class="login-button" value="로그인" @click="login">
            <p>{{ loginMessage }}</p>
        </form>
    </div>
</template>

<script setup>
import { ref } from "vue";

const userId = ref("");
const password = ref("");

const emit = defineEmits(["login-user"]);

const login = () => {
    let user = {
        userId: userId.value,
        password: password.value,
    };
    emit("login-user", user);
};

</script>


<style scoped>
.login-form {
    background: var(--header2);
    margin: 0 auto;
    width: 450px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 200px;
    border-radius: 20px;
    box-shadow: 0 0 10px var(--bg-400);
    backdrop-filter: blur(10px);
}

.form {
    width: 350px;
    padding: 3rem 2rem;
    border-radius: 1rem;
    /* box-shadow: 0 10px 25px rgba(92, 99, 105, 0.2); */
}

.form__title {
    font-weight: 500;
    margin-bottom: 2.5rem;
}

.login-div {
    position: relative;
    height: 52px;
    margin-bottom: 1.5rem;
}

.login-input {
    position: absolute;
    top: 0;
    left: 0;
    width: 90%;
    height: 50%;
    font-size: 1rem;
    border: 2px solid var(--primary-200);
    border-radius: 0.5rem;
    outline: none;
    padding: 1rem;
    background: none;
    z-index: 1;
}

.login-label {
    position: absolute;
    left: 1rem;
    top: 1.3rem;
    padding: 0 0.25rem;
    background-color: var(--header2);
    color: var(--text-200);
    font-size: 1rem;
    transition: 0.3s;
    border-radius: 10px;
}

.login-button {
    display: block;
    margin-left: auto;
    padding: 0.75rem 2rem;
    outline: none;
    border: none;
    background-color: var(--primary-200);
    color: #fff;
    font-size: 1rem;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
}

.login-button:hover {
    background-color: var(--primary-100);
}

.login-input:focus+.login-label {
    top: -0.3rem;
    left: 0.8rem;
    color: var(--primary-200);
    font-size: .75rem;
    font-weight: 500;
    z-index: 10;
}

.login-input:not(:placeholder-shown).login-input:not(:focus)+.login-label {
    top: -0.3rem;
    left: 0.8rem;
    z-index: 10;
    font-size: .75rem;
    font-weight: 500;
}

.login-input:focus {
    border: 2px solid var(--primary-100);
}
</style>