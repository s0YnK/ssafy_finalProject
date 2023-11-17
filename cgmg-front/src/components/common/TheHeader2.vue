<template>
    <div class="header2">
        <label class="switch">
            <input class="check" type="checkbox" id="darkModeToggle" />
            <span class="slider"></span>
        </label>

        <div class="log-reg">
            <RouterLink class="my-link" :class="{ click: isClick[0].value }" :to="{ name: 'my' }" v-if="getUser"
                @click="togggle(0)">my</RouterLink>
            <a href="#" class="my-link" v-if="getUser" @click="logout">logout</a>
            <RouterLink class="login-link" :class="{ click: isClick[1].value }" :to="{ name: 'login' }" v-else
                @click="togggle(1)">
                login</RouterLink>
            <RouterLink class="regist-link" :class="{ click: isClick[2].value }" :to="{ name: 'regist' }" v-if="!getUser"
                @click="togggle(2)">regist</RouterLink>
        </div>
        <SearchComp />
    </div>
</template>

<script setup>
import SearchComp from '../SearchComp.vue';
import { computed, ref } from "vue";

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
    if (a == 3) {
        window.location.href = "http://localhost:5173/"
    }
}
</script>

<style scoped>
@import '../../assets/css/toggle.css';

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
    gap: 20px;
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
</style>