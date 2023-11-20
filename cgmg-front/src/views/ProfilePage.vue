<template>
    <div>
        <h1>마이페이지</h1>
        <h2>{{ userId }} 님 환영합니다!</h2>
        <div class="info">
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
                            <tr v-for="follow, i in store.followerList" :key="follow.followerId">
                                <td>{{ i + 1 }}</td>
                                <td>
                                    <RouterLink :to="`/otherProfile/${follow.followerId}`">{{ follow.followerId }}
                                    </RouterLink>
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
                            <tr v-for="follow, i in store.followingList" :key="follow.followingId">
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
        </div>
        <div class="ctr-div">
            <StreakComp class="SC" />
            <div class="ctr">스트릭</div>
        </div>
        <div>
            <div>내가 좋아요한 게시물</div>
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
                <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                <tr v-for="board, i in store2.LikeList" :key="board.id">
                    <td>{{ i + 1 }}</td>
                    <RouterLink :to="`/community/${board.id}`">
                        <td>{{ board.title }}</td>
                    </RouterLink>
                    <td>{{ board.writer }}</td>
                    <td>{{ board.regDate }}</td>
                    <td>{{ board.viewCnt }}</td>
                </tr>
            </table>
        </div>
        <div>
            <div>내가 작성한 게시물</div>
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
                <!-- 검색 결과를 반복하면서 테이블 행을 생성 -->
                <tr v-for="board, i in store2.MyList" :key="board.id">
                    <td>{{ i + 1 }}</td>
                    <RouterLink :to="`/community/${board.id}`">
                        <td>{{ board.title }}</td>
                    </RouterLink>
                    <td>{{ board.writer }}</td>
                    <td>{{ board.regDate }}</td>
                    <td>{{ board.viewCnt }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>

<script setup>
import StreakComp from '../components/profile/StreakComp.vue';
import ChartComp from '../components/profile/ChartComp.vue';
import { useRoute, useRouter } from 'vue-router';
import { onMounted } from "vue";
import { useUserStore } from "../stores/user"
import { useBoardStore } from '../stores/community';
const store2 = useBoardStore();
const store = useUserStore();
const userId = JSON.parse(localStorage.getItem("loginUser")).userId;

onMounted(() => {
    store2.getMyList(userId);
    store2.getLikeList(userId);
    store.getfollower(userId);
    store.getfollowing(userId);
    console.log(2)
});


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

.follow {
    width: 300px;
    height: 300px;
    display: flex;
    background-color: var(--primary-200);
}

.info {
    display: flex;
}
</style>
