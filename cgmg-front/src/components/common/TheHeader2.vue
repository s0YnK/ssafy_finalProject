<template>
    <div class="header2">
        <div class="flex">
            <RouterLink :class="{ click: isClick[0].value }" :to="{ name: 'profile' }" v-if="getUser" @click="togggle(0)">
                <img :src="`src/assets/${store?.profile?.profileImg}.png`" class="iiimg">
            </RouterLink>
            <div class="user-info" v-if="getUser">
                <RouterLink class="my-link" :class="{ click: isClick[0].value }" :to="{ name: 'profile' }" v-if="getUser"
                    @click="togggle(0)">{{ store.profile.nickName }}</RouterLink>
                <div>{{ greetings[currentGreetingIndex] }}</div>
                <!-- 다른 정보도 추가할 예정 -->
            </div>
        </div>

        <div class="flex">
            <div class="log-reg">
                <div v-if="getUser">{{ store.profile.continuedStreak }} 일째 운동중! &nbsp;&nbsp;</div>
                <div v-if="getUser">{{ store.profile.totalExerciseCnt }}근&nbsp;&nbsp;</div>
                <div v-if="getUser">{{ Math.floor(store.profile.totalExerciseCnt / 100) + 1 }} 레벨&nbsp;&nbsp;</div>
                <a href="#" class="my-link" v-if="getUser" @click="logout">로그아웃&nbsp;&nbsp;</a>
                <RouterLink class="login-link" :class="{ click: isClick[1].value }" :to="{ name: 'login' }" v-else
                    @click="togggle(1)">
                    로그인</RouterLink>
                <RouterLink class="regist-link" :class="{ click: isClick[2].value }" :to="{ name: 'regist' }"
                    v-if="!getUser" @click="togggle(2)">회원가입</RouterLink>
                <RouterLink class="my-link" :class="{ click: isClick[3].value }" :to="{ name: 'setting' }" v-if="getUser"
                    @click="togggle(3)">
                    설정</RouterLink>
            </div>
            <ToggleComp />
            <SearchComp />
        </div>
    </div>
</template>

<script setup>
import SearchComp from './SearchComp.vue';
import ToggleComp from './ToggleComp.vue';
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from "@/stores/user";
import { onMounted, computed, ref, onBeforeUnmount } from "vue";
import axios from 'axios'

const store = useUserStore()

onMounted(() => {
    store.getProfile(JSON.parse(localStorage?.getItem("loginUser")).userId)
})


const props = defineProps(["user"]);
const emits = defineEmits(["logout"]);

const getUser = computed(() => !!props.user);

const logout = () => {
    emits("logout");
};
const isClick = ref([ref(false), ref(false), ref(false), ref(false), ref(false)])

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


const greetings = ref([
    "환영합니다.",
    "즐거운 운동되고 있나요?",
    "오늘도 힘찬 운동 시작!",
    "건강한 하루 시작하세요!",
    "운동은 지금이죠. 화이팅!"
]);

let currentGreetingIndex = ref(0);

const changeGreeting = () => {
    currentGreetingIndex.value = (currentGreetingIndex.value + 1) % greetings.value.length;
};

// 5초마다 changeGreeting 함수 호출
setInterval(changeGreeting, 5000);

</script>

<style scoped>
.flex {
    display: flex;
    align-items: center;
    gap: 15px;
}

.iiimg {
    margin-top: 5px;
    width: 30px;
    height: 30px;
}

.click {
    color: var(--primary-100)
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
    color: var(--primary-100)
}

.header2 {
    /* position: fixed; */
    top: 0;
    right: 0;
    padding: 15px 50px 15px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 10px;
    background: var(--header);
    backdrop-filter: blur(10px);
    /* width: 100%; */
}

.search-id-div {
    margin-left: 30px;
}

.sub-link {
    color: var(--text-100);
}

.log-reg,
.user-info {
    display: flex;
    gap: 10px;
}
</style>