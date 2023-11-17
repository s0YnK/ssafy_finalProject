<template>
    <div>
        <div class="header2-div">
            <TheHeader2 :user="user" @logout="logout" />
        </div>
        <div class="header-div">
            <TheHeader />
        </div>
        <div class="router-div">
            <RouterView @login-user="loginUser" @create-user="createUser" />
        </div>
    </div>
</template>

<script setup>
import TheHeader from '@/components/common/TheHeader.vue';
import TheHeader2 from '@/components/common/TheHeader2.vue';
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();
const user = ref(null);

onMounted(() => {
    const savedUser = localStorage.getItem("loginUser");
    if (savedUser) {
        user.value = JSON.parse(savedUser);
    }
});

const logout = () => {
    axios.get("http://localhost:8080/user-api/logout")
    user.value = null;
    localStorage.removeItem("loginUser");
    alert("로그아웃 했습니다.");
};

const loginUser = (loginUser) => {
    axios.post('http://localhost:8080/user-api/login', loginUser)
        .then(response => {
            localStorage.setItem("loginUser", JSON.stringify(response.data));
            console.log(response.data);
            window.location.href = "http://localhost:5173/"
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
};

const createUser = (createUser) => {
    const user = {
        userId: createUser.userId,
        password: createUser.password,
        name: createUser.name,
        email: createUser.email,
        nickName: createUser.nickName,
        profileImg: createUser.profileImg,
    };

    axios.post('http://localhost:8080/user-api/user', user)
        .then(response => {
            console.log(response.data);
            loginUser(user)
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
};

</script>
<style scoped>
.header-div {
    background-color: var(--bg-200);
    width: 250px;
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    box-shadow: 0 0 10px var(--bg-300);
}

.header2-div {
    margin-left: 250px;
}

.router-div {
    margin-top: 70px;
    margin-left: 250px;
}
</style>

