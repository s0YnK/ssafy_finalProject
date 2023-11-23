<template>
    <div class="update-form">
        <form action="" class="form" @submit.prevent="onsubmit">
            <h1 class="form__title">수정페이지</h1>

            <div class="update-div">
                <input type="text" class="update-input" placeholder=" " v-model="data.userId" readonly>
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
                <input type="text" class="update-input" placeholder=" " v-model="data.name" readonly>
                <label class="update-label">이름 (변경불가)</label>
            </div>
            <div class="update-div">
                <input type="text" class="update-input" placeholder=" " v-model="data.nickName">
                <label class="update-label">닉네임</label>
            </div>
            <button @click="deleteId">회원탈퇴</button>
            <input type="submit" class="update-button" value="update" @click="update">
        </form>
        <div class="imgbox">
            <img src="@/assets/1.png" class="proimg" @click="selectProfileImage(1)">
            <img src="@/assets/2.png" class="proimg" @click="selectProfileImage(2)">
            <img src="@/assets/3.png" class="proimg" @click="selectProfileImage(3)">
            <img src="@/assets/4.png" class="proimg" @click="selectProfileImage(4)">
            <img src="@/assets/5.png" class="proimg" @click="selectProfileImage(5)">
            <img src="@/assets/6.png" class="proimg" @click="selectProfileImage(6)">
            <img src="@/assets/7.png" class="proimg" @click="selectProfileImage(7)">
            <img src="@/assets/8.png" class="proimg" @click="selectProfileImage(8)">
        </div>
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
const selectProfileImage = (imageNumber) => {
    data.value.profileImg = `${imageNumber}`;

    // 모든 이미지에 있는 selected 클래스 제거
    document.querySelectorAll('.proimg').forEach(img => img.classList.remove('selected'));

    // 선택한 이미지에만 selected 클래스 추가
    document.querySelector(`.proimg:nth-child(${imageNumber})`).classList.add('selected');
};

const update = function () {
    console.log(data.value)
    store.updateUser(data.value)
}
const deleteId = function () {
    console.log(user.userId)
    store.deleteUser(user.userId)
    user.value = null;
    localStorage.removeItem("loginUser");
    alert("회원탈퇴 완료. \n그동안 고마웠습니다.");
}
</script>

<style scoped>
.proimg.selected {
    border: 5px solid var(--text-200);
    border-radius: 100px;
    margin: 5px;
}

.proimg {
    width: 80px;
    height: 80px;
    margin: 10px;
}

.imgbox {
    background-color: var(--bg-400);
    width: 200px;
    height: 420px;
    border-radius: 20px;
    padding: 20px;
    box-shadow: inset 0 0 10px var(--text-200);
}

.update-form {
    background: var(--header2);
    margin: 0 auto;
    width: 700px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 100px;
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
    border: 2px solid var(--primary-200);
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
    background-color: var(--header2);
    color: var(--text-200);
    font-size: 1rem;
    transition: 0.3s;
}

.update-button {
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

.update-button:hover {
    background-color: var(--primary-100);
}

.update-input:focus+.update-label {
    top: -0.3rem;
    left: 0.8rem;
    color: var(--primary-200);
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
    border: 2px solid var(--primary-100);
}
</style>