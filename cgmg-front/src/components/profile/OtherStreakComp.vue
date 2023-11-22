<template>
    <div class="streak">
        <div class="cont">
            <svg width="1050" height="230" class="js-calendar-graph-svg d" id="dd">
                <!-- Graph -->
                <g transform="translate(11, 11)" id="graph"></g>

                <!-- Legend for contribution levels -->
                <g transform="translate(11, 33)">
                    <rect class="legend" width="11" height="11" x="50" y="130" rx="3" fill="#ebedf0"></rect>
                    <text x="65" y="141" class="legend-text">0</text>
                    <rect class="legend" width="11" height="11" x="90" y="130" rx="3" fill="#9be9a8"></rect>
                    <text x="105" y="141" class="legend-text">1</text>
                    <rect class="legend" width="11" height="11" x="130" y="130" rx="3" fill="#40c463"></rect>
                    <text x="145" y="141" class="legend-text">2</text>
                    <rect class="legend" width="11" height="11" x="170" y="130" rx="3" fill="#40c463"></rect>
                    <text x="185" y="141" class="legend-text">3</text>
                    <rect class="legend" width="11" height="11" x="210" y="130" rx="3" fill="#40c463"></rect>
                    <text x="225" y="141" class="legend-text">4+</text>
                </g>
            </svg>
        </div>

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import moment from 'moment';
import axios from 'axios';

const data = ref([]);
const svg = ref(null);
const startDate = moment(new Date() - 1000 * 60 * 60 * 24 * 364);
const endDate = moment(new Date());
let currentDate = startDate.clone();
let x = 0;
let y = 0;
if (startDate.isoWeekday() !== 7) {
    y = startDate.isoWeekday() * 19;
}
const monthColors = [
    "#ff2e2e", "#ff7b2e", "#ffba26", "#e2ff26", "#74ff2b", "#29ff50",
    "#29ffd2", "#26c5ff", "#266afc", "#3826ff", "#a526ff", "#ff26e2"
];

onMounted(async () => {
    try {
        const route = useRoute()
        const response = await axios.get('http://localhost:8080/exercise-api/exercise/log', {
            params: {
                userId: route.params.userId
            }
        });
        // console.log(JSON.parse(localStorage.getItem("loginUser")).userId) //로컬스토리지 가져오기
        data.value = response.data;
        // console.log(data.value[1])
        svg.value = document.getElementById("graph");
        createGraph();
        addEventListeners();
        for (let i = 0; i < data.value.length; i++) {
            const selectedDay = svg.value.querySelector(`[data-date='${data.value[i].regDate}']`);
            const monthIndex = moment(data.value[i].regDate).month();
            selectedDay.setAttribute('fill', monthColors[monthIndex]);
            if (i === 0) {
                selectedDay.setAttribute("exerciseName", "");
            }

            // 기존 값이 있는 경우에만 추가
            const currentExerciseName = selectedDay.getAttribute("exerciseName");
            const updatedExerciseName = currentExerciseName ? currentExerciseName + `${data.value[i].exerciseName} : ${data.value[i].cnt}셋트<br>` : `<hr style="margin:10px 0px;">${data.value[i].exerciseName} : ${data.value[i].cnt}셋트<br>`;
            selectedDay.setAttribute("exerciseName", updatedExerciseName);
            selectedDay.setAttribute("data-count", parseInt(selectedDay.getAttribute("data-count")) + data.value[i].cnt);
            selectedDay.setAttribute("fill-opacity", getColorForCount(parseInt(selectedDay.getAttribute("data-count")) + data.value[i].cnt));
        }

    } catch (error) {
        console.error('Error fetching data:', error);
    }
});

const createGraph = () => {
    while (currentDate.isSameOrBefore(endDate, 'day')) {
        const rect = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
        rect.setAttribute('class', 'day');
        rect.setAttribute('width', '16');
        rect.setAttribute('height', '16');
        rect.setAttribute('x', x);
        rect.setAttribute('y', y);
        rect.setAttribute('fill', 'var(--text-300)');
        rect.setAttribute("fill-opacity", '50%');
        rect.setAttribute('data-count', '0');
        rect.setAttribute("rx", 5)
        rect.setAttribute('data-date', currentDate.format('YYYY-MM-DD'));
        svg.value.appendChild(rect);

        currentDate.add(1, 'day');
        y += 19;

        if (currentDate.isoWeekday() === 7) {
            y = 0;
            x += 19;
        }
    }
};

const addEventListeners = () => {
    const rects = svg.value.querySelectorAll('.day');
    rects.forEach((rect) => {
        rect.addEventListener('mouseover', handleMouseOver);
        rect.addEventListener('mouseout', handleMouseOut);
    });
};

const handleMouseOver = (event) => {
    const rect = event.target;
    const date = rect.getAttribute('data-date')
    const count = rect.getAttribute('data-count');
    const content = rect.getAttribute('exerciseName');
    showCountLabel(rect, count, date, content);
};

const handleMouseOut = (event) => {
    const rect = event.target;
    hideCountLabel();
};
const showCountLabel = (rect, count, date, content) => {
    // 이전에 생성된 레이블이 있다면 삭제
    hideCountLabel();

    // 여기서 화면의 가운데에 나타낼 것들을 설정해줄거야.
    const label = document.createElement('div');
    label.className = 'count-label';

    // 내용 채우기
    if (content === null) {
        label.innerHTML = `<p>${date}에는<br> 운동을 하지 않았습니다.</p>`
    } else {
        label.innerHTML = `
            <p>${date}에 <br>총 ${count}셋트를 운동했습니다.</p>
            <p>${content}</p>
        `;
    }

    // 화면 가운데로 오게 만들어줄거야.
    const rectBounds = rect.getBoundingClientRect();
    const centerX = rectBounds.left + rectBounds.width / 2;
    const centerY = rectBounds.top + rectBounds.height / 2;

    // 스크롤 위치 고려
    const scrollX = window.scrollX || window.pageXOffset;
    const scrollY = window.scrollY || window.pageYOffset;

    label.style.position = 'absolute';
    label.style.left = centerX + scrollX + 'px';
    label.style.top = centerY + scrollY + 'px';
    label.style.transform = 'translate(-50%, -120%) scale(1)';
    label.style.transition = 'transform 0.3s ease-in-out';

    // body에 추가해줘.
    document.body.appendChild(label);

    // 애니메이션 효과 주기
    setTimeout(() => {
        label.style.transform = 'translate(-50%, -130%) scale(1)';
    }, 0);

    // 이벤트 리스너 추가해서 마우스가 벗어났을 때 숨기도록
    rect.addEventListener('mouseout', () => {
        hideCountLabel();
    });
};

const hideCountLabel = () => {
    const container = document.querySelector('.count-label');
    if (container) {
        container.remove();
    }
};
// const showCountLabel = (rect, count, date, content) => {
//     const label = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
//     const text = document.createElementNS('http://www.w3.org/2000/svg', 'text');

//     label.setAttribute('class', 'count-label');
//     label.setAttribute('x', parseFloat(rect.getAttribute('x')) - 200);
//     label.setAttribute('y', parseFloat(rect.getAttribute('y')) - 83);
//     label.setAttribute('rx', '5')
//     label.setAttribute('fill', `#A6B1E1`);
//     label.setAttribute('width', '270');
//     label.setAttribute('height', '80');

//     text.setAttribute('class', 'count-text');
//     text.setAttribute('x', parseFloat(rect.getAttribute('x')) - 195);
//     text.setAttribute('y', parseFloat(rect.getAttribute('y')) - 65);
//     text.setAttribute('font-size', '15px');
//     text.setAttribute('fill', '#292e3b');

//     const lines = (date + '의 총 운동량은 ' + count + '입니다.\n' + content).split('\n');

//     lines.forEach((line, index) => {
//         const tspan = document.createElementNS('http://www.w3.org/2000/svg', 'tspan');
//         tspan.setAttribute('x', parseFloat(rect.getAttribute('x')) - 195);
//         tspan.setAttribute('dy', index === 0 ? '0' : '1.2em');
//         tspan.textContent = line;
//         text.appendChild(tspan);
//     });

//     svg.value.appendChild(label);
//     svg.value.appendChild(text);

//     // console.log(text.textContent);
// };

// const hideCountLabel = () => {
//     const label = svg.value.querySelector('.count-label');
//     const text = svg.value.querySelector('.count-text');
//     if (label) {
//         label.remove();
//     }
//     if (text) {
//         text.remove();
//     }
// };


const getColorForCount = (count) => {
    const a = Math.floor(count / 10)
    // console.log(a)
    switch (a) {
        case 0:
            return '30%';
        case 1:
            return '40%';
        case 2:
            return '60%';
        case 3:
            return '80%'
        default:
            return '100%';
    }
};
</script>

<style>
.count-label {
    width: 180px;
    padding: 20px;
    border-radius: 20px;
    box-shadow: 0 0 10px #aaaaaa;
    background-color: rgba(255, 255, 192, 0.1);
    color: var(--text-200);
    backdrop-filter: blur(10px);
}

.cont::-webkit-scrollbar {
    display: none;
}

.streak {
    width: 80%;
    height: 300px;
    background-color: var(--bg-500);
}

.label {
    font-size: 15px;
    overflow: scroll;
}

.day:hover {
    stroke-width: 2;
    stroke: #444444;
}

.cont {
    height: 600px;
    overflow: scroll;
}

.d {
    margin: 20px;
    margin-top: 60px;
}
</style>
