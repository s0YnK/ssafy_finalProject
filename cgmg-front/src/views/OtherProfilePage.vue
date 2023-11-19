<template>
    <div>
        <h1>{{ route.params.userId }}님의 페이지</h1>
        <button v-if="isFollowing()" @click="followAdd">팔로우하기</button>
        <button v-if="isNotFollowing()" @click="followDelete">팔로우취소</button>
        <ChartComp />
        <div class="follow">
            <div>
                <div>팔로워 {{ store.followerList.length }}명</div>
                <div>
                    <table>
                        <tr>
                            <th>번호</th>
                            <th>아이디</th>
                            <th>닉네임</th>
                            <th>티어</th>
                        </tr>
                        <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                        <tr v-for="follow, i in followerList" :key="follow.followerId">
                            <td>{{ i + 1 }}</td>
                            <td>
                                <RouterLink :to="`/otherProfile/${follow.followerId}`">{{ follow.followerId }}</RouterLink>
                            </td>
                            <td>{{ follow.nickName }}</td>
                            <td>{{ follow.totalExerciseCnt }}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div>
                <div>팔로잉 {{ store.followingList.length }}명</div>
                <div>
                    <table>
                        <tr>
                            <th>번호</th>
                            <th>아이디</th>
                            <th>닉네임</th>
                            <th>티어</th>
                        </tr>
                        <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                        <tr v-for="follow, i in followingList" :key="follow.followingId">
                            <td>{{ i + 1 }}</td>
                            <td>
                                <RouterLink :to="`/otherProfile/${follow.followingId}`">{{ follow.followingId }}
                                </RouterLink>
                            </td>
                            <td>{{ follow.nickName }}</td>
                            <td>{{ follow.totalExerciseCnt }}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="ctr-div">
            <StreakComp class="SC" />
            <div class="ctr">스트릭</div>
        </div>
    </div>
</template>

<script setup>
import StreakComp from '../components/profile/OtherStreakComp.vue';
import ChartComp from '../components/profile/OtherChartComp.vue';
import { useRoute, useRouter } from 'vue-router'
import { onMounted, ref, computed } from "vue";
import axios from 'axios'
import { useUserStore } from "../stores/user"
const store = useUserStore();

onMounted(() => {
    const route = useRoute();
    store.getfollower(route.params.userId); // 주소에서 검색어 가져오기
    store.getfollowing(route.params.userId); // 주소에서 검색어 가져오기
})
const route = useRoute();

const data = ref({
    userId: JSON.parse(localStorage.getItem("loginUser")).userId,
    followingId: route.params.userId,
})

const followerList = computed(() => store.followerList)
const followingList = computed(() => store.followingList)

function followAdd() {
    store.followAdd(data.value)
}
function followDelete() {
    store.followDelete(data.value)
}

const isFollowing = () => {
    // 로그인한 사용자 정보 가져오기
    const loginUser = JSON.parse(localStorage.getItem("loginUser"));
    // 해당 사용자가 팔로잉 중인지 여부 확인
    return store.followerList.length === 0 || store.followerList.every(follow => follow.followerId !== loginUser.userId);
};
const isNotFollowing = () => {
    // 로그인한 사용자 정보 가져오기
    const loginUser = JSON.parse(localStorage.getItem("loginUser"));
    // 해당 사용자가 팔로잉 중인지 여부 확인
    return store.followerList.length !== 0 && store.followerList.some(follow => follow.followerId === loginUser.userId);
};
</script>

<style scoped>
h1 {
    margin: 40px;
}

.SC {
    width: 90%;
    margin: 20px auto;
    border-radius: 20px;
    padding: 20px;
}

.ctr-div {
    position: relative;
}

.ctr {
    position: absolute;
    top: 20px;
    left: 70px;
}
</style>
