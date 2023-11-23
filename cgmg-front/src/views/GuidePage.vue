<template>
    <div class="background"></div>
    <div class="search-div">
        <h1 class="dis">운동법 가이드</h1>
        <input type="text" id="keywords" class="guide-input" v-model="keyword" placeholder="모르는 운동을 검색해 보세요" />
        <button @click="searchByName" class="s-button dis">입력</button>
    </div>
    <div class="ans">
        <div class="ans-left">
            <div>{{ keyword }} 운동방법.</div>
            <div class="ans-gpt">
                <div>
                    <div v-if="loading" class="loading">
                        <div class="loading-gif">
                            <img src="https://cdn.dribbble.com/users/1018473/screenshots/3963419/loader.gif" />
                        </div>
                        <p class="text">AI가 <br>{{ keyword }}의 <br>운동방법을 적고 있어요</p>
                    </div>
                    <div v-if="gptResponse" class="gpt-response">
                        <div v-html="gptResponse"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ans-diveo">
            <ul v-for="video in store.videos" :key="video.id.videoId">
                <li class="ans-itm">
                    <iframe width="360" height="180" :src="`https://www.youtube.com/embed/${video.id.videoId}`"
                        title="YouTube video player" frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                        allowfullscreen class="ans-vid"></iframe>
                    <div class="ans-span">
                        <span>{{ video.snippet.title }}</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
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
    window.scrollBy({ top: 2000, left: 0, behavior: 'smooth' });
    // GPT API 호출
    const response = await callGPT(keyword.value);
    // 로딩 종료
    loading.value = false;
    // GPT API 응답 저장
    gptResponse.value = response.choices[0].message.content.replace(/\n/g, "<br>");

    // 여기서 필요한 작업을 수행하면 돼!
    console.log('GPT API 응답:', gptResponse.value);
};

// GPT API 호출 함수 정의
const callGPT = async (query) => {
    const api_key = "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";
    const data = {
        model: 'gpt-3.5-turbo-1106',
        temperature: 0.5,
        max_tokens: 1000,
        top_p: 0.3,
        messages: [
            { role: 'system', content: 'You are a helpful assistant.' },
            { role: 'user', content: 'Teach me how to do ' + query + 'and the order in which they are done. Order them by number. Please also tell me what to keep in mind when exercising. write these two separately in the table of contents. Do not write down what table of contents there is. Please answer in Korean. And please tell me only the content, excluding the introduction and conclusion. Press the Enter key every time you finish a sentence.' },
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
.ans-left {
    flex-grow: 1;
}

.loading {
    text-align: center;
    border-radius: 20px;
    overflow: hidden;
}

.loading-gif {
    padding: 40px;
}

.loading img {
    width: 500px;
    border-radius: 20px;
}

.text {
    font-size: 20px;
}

.gpt-response {
    margin-top: 20px;
    padding: 20px;
    border-radius: 20px;
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

.ans {
    display: flex;
    width: 1150px;
    margin: 0 auto;
    gap: 20px;
    margin-bottom: 20px;
}

.ans-gpt {
    background-color: var(--bg-500);
    border-radius: 20px;
    box-shadow: 0 0 10px var(--text-300);
    padding: 20px;
    margin: 70px 0;
}

.ans-diveo {
    width: 400px;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.ans-vid {
    border-radius: 20px 20px 0 0;
}

.ans-span {
    padding: 10px;
}

.ans-itm {
    background-color: var(--bg-500);
    border-radius: 20px;
    box-shadow: 0 0 10px var(--text-300);
}

ul {
    list-style: none;
}

.s-button {
    margin-top: 30px;
    padding: 0.75rem 2rem;
    outline: none;
    border: none;
    background-color: var(--primary-200);
    color: #fff;
    font-size: 1rem;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
    margin-bottom: 400px;
}

.s-button:hover {
    background-color: var(--primary-100);
}
</style>
