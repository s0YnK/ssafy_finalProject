<template>
    <div class="background"></div>
    <div class="search-div">
        <h1 class="dis">운동법 가이드</h1>
        <input type="text" id="keywords" class="guide-input" v-model="keyword" placeholder="모르는 운동을 검색해 보세요" />
        <button @click="searchByName" class="guide-btn dis">입력</button>
    </div>
    <div v-if="loading" class="loading">
        <img src="https://studentrights.sen.go.kr/images/common/loading.gif" />
        <p>Loading...</p>
    </div>
    <div v-if="gptResponse" class="gpt-response">
        <pre>{{ gptResponse.choices[0].message.content }}</pre>
    </div>
    <ul v-for="video in store.videos" :key="video.id.videoId">
        <li class="a">
            <span>{{ video.snippet.title }}</span>
            <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${video.id.videoId}`"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>
        </li>
    </ul>
</template>

<script setup>
import { ref } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';

const keyword = ref('');
const store = useYoutubeStore();
const loading = ref(false);
const gptResponse = ref(null);

const searchByName = async () => {
    // YouTube 검색
    store.youtubeSearch(keyword.value);

    // 로딩 시작
    loading.value = true;

    // GPT API 호출
    const response = await callGPT(keyword.value);

    // 로딩 종료
    loading.value = false;

    // GPT API 응답 저장
    gptResponse.value = response;

    // 여기서 필요한 작업을 수행하면 돼!
    console.log('GPT API 응답:', gptResponse.value);
};

// GPT API 호출 함수 정의
const callGPT = async (query) => {
    const api_key = "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";
    const data = {
        model: 'gpt-3.5-turbo',
        temperature: 0.5,
        max_tokens: 500,
        top_p: 0.3,
        messages: [
            { role: 'system', content: 'You are a helpful assistant.' },
            { role: 'user', content: 'Teach me how to do ' + query + 'and the order in which they are done. Please answer in Korean. And please tell me only the content, excluding the introduction and conclusion.' },
        ],
    };

    const response = await fetch(
        "https://api.openai.com/v1/chat/completions",
        {
            method: 'POST',
            headers: {
                Authorization: "Bearer " + api_key,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        }
    );

    return await response.json();
};
</script>

<style scoped>
.loading {
    text-align: center;
}

.loading img {
    position: absolute;
    top: 40%;
    left: 45%;
    z-index: 100;
}

.loading p {
    position: absolute;
    top: 57%;
    left: 43%;
    z-index: 101;
}

.gpt-response {
    margin-top: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.a0,
.a1,
.a {
    width: 18%;
    height: 36%;
    background-color: var(--bg-200);
    /* top: 100%; */
    border-radius: 20px;
    transition: 1s;
}

.a1 {
    left: 30%;
}

.a2 {
    left: 50%;
}

.a3 {
    left: 70%;
}

.search-div {
    margin: 300px auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: 0.4s;
}

.guide-input {
    font-size: 20px;
    margin-top: 20px;
    width: 240px;
    height: 70px;
    border-radius: 30px;
    border: solid 3px var(--primary-200);
    padding-left: 60px;
    padding-right: 40px;
    outline: none;
    color: var(--text-100);
    background-color: rgba(255, 255, 255, 0.3);
    transition: 0.4s;
}

.guide-input:focus {
    /* background-color: azure; */
    border: 3px solid var(--primary-100);
    box-shadow: 0px 0px 5px var(--primary-200);
    background-color: var(--bg-100);
    background-position: 2%;
    transition: 0.4s;
}

.guide-btn {
    margin-top: 20px;
}
</style>
