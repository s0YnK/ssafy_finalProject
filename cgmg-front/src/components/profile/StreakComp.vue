<template>
    <div class="streak">
        <div class="cont">
            <svg width="1450" height="250" class="js-calendar-graph-svg d" id="dd">
                <!-- Graph -->
                <g transform="translate(11, 11)" id="graph"></g>

                <!-- Legend for contribution levels -->
                <g transform="translate(11, 33)">
                    <rect class="legend" width="11" height="11" x="50" y="180" rx="3" fill="#ebedf0"></rect>
                    <text x="65" y="191" class="legend-text">0</text>
                    <rect class="legend" width="11" height="11" x="90" y="180" rx="3" fill="#9be9a8"></rect>
                    <text x="105" y="191" class="legend-text">1</text>
                    <rect class="legend" width="11" height="11" x="130" y="180" rx="3" fill="#40c463"></rect>
                    <text x="145" y="191" class="legend-text">2</text>
                    <rect class="legend" width="11" height="11" x="170" y="180" rx="3" fill="#40c463"></rect>
                    <text x="185" y="191" class="legend-text">3</text>
                    <rect class="legend" width="11" height="11" x="210" y="180" rx="3" fill="#40c463"></rect>
                    <text x="225" y="191" class="legend-text">4+</text>
                </g>
            </svg>
        </div>

    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import moment from 'moment';
import axios from 'axios';

const data = ref([]);
const svg = ref(null);
const startDate = moment(new Date() - 1000 * 60 * 60 * 24 * 365);
const endDate = moment(new Date());
let currentDate = startDate.clone();
let x = 50;
let y = 0;
const monthColors = [
    "#ff2e2e", "#ff7b2e", "#ffba26", "#e2ff26", "#74ff2b", "#29ff50",
    "#29ffd2", "#26c5ff", "#266afc", "#3826ff", "#a526ff", "#ff26e2"
];

onMounted(async () => {
    try {
        const response = await axios.get('http://localhost:8080/exercise-api/exercise/log', {
            params: {
                userId: JSON.parse(localStorage.getItem("loginUser")).userId
            }
        });
        console.log(JSON.parse(localStorage.getItem("loginUser")).userId)
        data.value = response.data;
        svg.value = document.getElementById("graph");
        createGraph();
        addEventListeners();
        for (let i = 0; i < data.value.length; i++) {
            const selectedDay = svg.value.querySelector(`[data-date='${data.value[i].regDate}']`);
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
        const monthIndex = moment(currentDate).month();
        rect.setAttribute('class', 'day');
        rect.setAttribute('width', '20');
        rect.setAttribute('height', '20');
        rect.setAttribute('x', x);
        rect.setAttribute('y', y);
        rect.setAttribute('fill', monthColors[monthIndex]);
        rect.setAttribute('fill-opacity', '0.2');
        rect.setAttribute('data-count', '0');
        rect.setAttribute("rx", 6)
        rect.setAttribute('data-date', currentDate.format('YYYY-MM-DD'));
        svg.value.appendChild(rect);

        currentDate.add(1, 'day');
        y += 25;

        if (currentDate.isoWeekday() === 7) {
            y = 0;
            x += 25;
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
    showCountLabel(rect, count, date);
};

const handleMouseOut = (event) => {
    const rect = event.target;
    hideCountLabel();
};

const showCountLabel = (rect, count, date) => {
    const label = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
    const text = document.createElementNS('http://www.w3.org/2000/svg', 'text');
    label.setAttribute('class', 'count-label');
    label.setAttribute('x', parseFloat(rect.getAttribute('x')) - 55);
    label.setAttribute('y', parseFloat(rect.getAttribute('y')) + 25);
    label.setAttribute('rx', '5')
    label.setAttribute('fill', `#555555`);
    label.setAttribute('width', '130');
    label.setAttribute('height', '30');
    label.setAttribute('text-anchor', 'middle');
    text.setAttribute('class', 'count-text');
    text.setAttribute('x', parseFloat(rect.getAttribute('x')) - 48);
    text.setAttribute('y', parseFloat(rect.getAttribute('y')) + 43);
    text.setAttribute('font-size', '10px');
    text.setAttribute('fill', '#ffffff');
    text.textContent = date + '의 근 획득량은 ' + count + '개 입니다.';
    svg.value.appendChild(label);
    svg.value.appendChild(text);
};

const hideCountLabel = () => {
    const label = svg.value.querySelector('.count-label');
    const text = svg.value.querySelector('.count-text');
    if (label) {
        label.remove();
    }
    if (text) {
        text.remove();
    }
};


const getColorForCount = (count) => {
    switch (+count) {
        case 0:
            return '0.2';
        case 1:
            return '0.4';
        case 2:
            return '0.7';
        case 3:
            return '1.0'
        default:
            return '1.0';
    }
};
</script>

<style>
.cont::-webkit-scrollbar {
    display: none;
}

.streak {
    width: 80%;
    height: 300px;
    background-color: var(--bg-200);
}

.label {
    font-size: 15px;
    overflow: scroll;
}

.day {
    transition: 0.5s;
    stroke-width: 2;
    stroke: #ffffff;
}

.day:hover {
    stroke-width: 2;
    stroke: #aaaaaa;
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
