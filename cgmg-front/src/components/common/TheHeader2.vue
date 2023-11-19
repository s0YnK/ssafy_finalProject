<template>
    <div class="header2">
        <div class="user-info" v-if="getUser">
            <div>{{ store.profile.nickName }}</div>|
            <div>{{ store.profile.continuedStreak }} 일째 운동중!</div>|
            <div>{{ store.profile.totalExerciseCnt }} 레벨</div>
            <!-- 다른 정보도 추가할 예정 -->
        </div>

        <div class="log-reg">
            <RouterLink class="my-link" :class="{ click: isClick[0].value }" :to="{ name: 'profile' }" v-if="getUser"
                @click="togggle(0)">마이페이지</RouterLink>
            <a href="#" class="my-link" v-if="getUser" @click="logout">로그아웃</a>
            <RouterLink class="login-link" :class="{ click: isClick[1].value }" :to="{ name: 'login' }" v-else
                @click="togggle(1)">
                로그인</RouterLink>
            <RouterLink class="regist-link" :class="{ click: isClick[2].value }" :to="{ name: 'regist' }" v-if="!getUser"
                @click="togggle(2)">회원가입</RouterLink>
        </div>
        <ToggleComp />
        <SearchComp />
    </div>
</template>

<script setup>
import SearchComp from './SearchComp.vue';
import ToggleComp from './ToggleComp.vue';
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from "@/stores/user";
import { onMounted, computed, ref } from "vue";
import axios from 'axios'

const store = useUserStore()

const route = useRoute();
const router = useRouter();
onMounted(() => {
    store.getProfile(JSON.parse(localStorage.getItem("loginUser")).userId)
})

// 삭제하는 부분 나중에 쓸 예정
// const deleteReview = function () {
//     axios.delete(`http://localhost:8080/api-review/review/${route.params.id}`)
//         .then(() => {
//             router.push({ name: 'reviewList' })
//         })
// }

// const userInfo = ref(JSON.parse(localStorage.getItem('loginUser')));

// // 로그인 정보가 바뀔 때마다 헤더 업데이트
// watchEffect(() => {
//     userInfo.value = JSON.parse(localStorage.getItem('loginUser'));
// });

const props = defineProps(["user"]);
const emits = defineEmits(["logout"]);

const getUser = computed(() => !!props.user);

const logout = () => {
    emits("logout");
};
const isClick = ref([ref(false), ref(false), ref(false), ref(false)])

const togggle = function (a) {
    console.log(isClick.value[a].value)
    for (let i = 0; i < 4; i++) {
        if (i == a) {
            isClick.value[i].value = true
        } else {
            isClick.value[i].value = false
        }
    }
}
</script>

<style scoped>
.click {
    color: rgb(55, 182, 140);
    margin-bottom: 10px;
}

.login-link,
.regist-link,
.my-link {
    font-weight: 600;
    transition: 0.2s;
}

.login-link:hover,
.regist-link:hover,
.my-link:hover {
    color: rgb(55, 182, 140);
    margin-bottom: 5px;
}

.header2 {
    position: fixed;
    top: 0;
    right: 0;
    padding: 15px 100px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    gap: 10px;
    background: var(--header);
    backdrop-filter: blur(10px);
    width: 100%;
}

.search-id-div {
    margin-left: 30px;
}

.sub-link {
    color: var(--text-100);
}

.user-info {
    display: flex;
    gap: 10px;
}
</style>