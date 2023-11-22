<template>
    <div class="mm">
        <div class="info">
            <div class="zz">
                <h2 class="h22"><span style="color: var(--primary-200);">{{ store.profile.nickName }}</span> 님 환영합니다!</h2>
                <div class="img-pro">
                    <img :src="`src/assets/${store.profile.profileImg}.png`" width="100" height="100" class="iimg">
                    <div class="pro">
                        <progress id="progress" :value="`${store.profile.totalExerciseCnt % 100}`" min="0"
                            max="100"></progress>
                        <div class="proinf">
                            <div>{{ Math.floor(store.profile.totalExerciseCnt / 100) * 100 }}</div>
                            <div style="font-size: 20px;">총 <span style="color: var(--primary-100);">{{
                                store.profile.totalExerciseCnt }}</span>근 획득
                                |
                                승급까지 {{ 100 - store.profile.totalExerciseCnt %
                                    100
                                }}근
                            </div>
                            <div>{{ Math.floor(store.profile.totalExerciseCnt / 100 + 1) * 100 }}</div>
                        </div>
                    </div>
                </div>
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
            <div class="cat">
                <ChartComp />
            </div>
        </div>
        <div class="ctr-div">
            <div class="ctr"> 운동을 <span style="color: var(--primary-200);">{{ store.profile.continuedStreak }}일</span> 연속
                하고있어요!</div>
            <StreakComp />
        </div>
        <div class="comm">
            <div @click="showLikeList = true; showMyList = false">내가 좋아요한 게시물</div>
            <div @click="showMyList = true; showLikeList = false">내가 작성한 게시물</div>
            <div v-if="showLikeList">
                <table>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회수</th>
                    </tr>
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
            <div v-if="showMyList">
                <table>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회수</th>
                    </tr>
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
    </div>
</template>

<script setup>
import StreakComp from '../components/profile/StreakComp.vue';
import ChartComp from '../components/profile/ChartComp.vue';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted } from "vue";
import { useUserStore } from "../stores/user"
import { useBoardStore } from '../stores/community';
const store2 = useBoardStore();
const store = useUserStore();
const userId = JSON.parse(localStorage.getItem("loginUser")).userId;
const showLikeList = ref(true);
const showMyList = ref(false);

onMounted(() => {
    store2.getMyList(userId);
    store2.getLikeList(userId);
    store.getfollower(userId);
    store.getfollowing(userId);
    store.getProfile(userId);
    console.log(store.profile.profileImg)
});

</script>

<style scoped>
.mm {
    width: 1200px;
    margin: 100px auto 0px;
}

.h22 {
    padding: 20px 0 20px 10px;
}

.SC {
    width: 90%;
    margin: 20px auto;
    border-radius: 20px;
    padding: 20px;
    transition: 0.2s;
}

.ctr-div {
    width: 1120px;
    margin: 20px auto;
    background-color: var(--bg-500);
    border-radius: 20px;
    transition: 0.2s;
}

.ctr-div:hover {
    box-shadow: 0 0 10px var(--text-200)
}

.ctr {
    padding: 20px 0px 0px 40px;
    font-size: 20px;
}

.info {
    width: 1120px;
    display: flex;
    margin: 20px auto;
    gap: 20px;
}


.follow {
    width: 350px;
    display: flex;
    background-color: var(--primary-300);
    border-radius: 20px;
    padding: 20px;
    transition: 0.2s;
}

.zz {
    flex-grow: 1;
}

.pro {
    flex-grow: 1;
    margin-top: 15px;
}

.proinf {
    display: flex;
    justify-content: space-between;
    color: var(--text-200);
}

.img-pro {
    display: flex;
    /* justify-content: center; */
    /* align-items: center; */
    gap: 20px;
    margin: 20px;
}

.iimg {
    border-radius: 100px;
    border: 3px solid var(--text-200);
}

.follow:hover {
    box-shadow: 0 0 10px var(--text-200)
}

.comm {
    width: 1120px;
    display: flex;
    margin: 20px auto;
    gap: 20px;
}


#progress {
    width: 100%;
    margin-top: 20px;
    appearance: none;
    height: 30px;
}

#progress::-webkit-progress-bar {
    background: var(--header3);
    border-radius: 10px;
    box-shadow: inset 3px 3px 10px var(--header2);
}

#progress::-webkit-progress-value {
    border-radius: 10px;
    background: #1D976C;
    background: -webkit-linear-gradient(to right, var(--primary-200), var(--primary-100));
    background: linear-gradient(to right, var(--primary-200), var(--primary-100));
}
</style>
