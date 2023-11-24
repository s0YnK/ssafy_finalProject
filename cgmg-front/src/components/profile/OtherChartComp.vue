<template>
    <div class="chart">
        <!-- {{ data }} -->
        <canvas id="myChart" width="100" height="100"></canvas>
        <div v-if="data.length == 0" class="no">
            <div class="inf">아직 한번도 <br>운동을 하지 않은 유저예요</div>
        </div>
    </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Chart from 'chart.js/auto';
import axios from 'axios';

export default {
    setup() {
        const data = ref([]);

        onMounted(async () => {
            try {
                const route = useRoute()
                const response = await axios.get('http://localhost:8080/exercise-api/exercise/log/part', {
                    params: {
                        userId: route.params.userId
                    }
                });

                data.value = response.data;
                console.log(data.value.length);

                // bodyPart와 cnt 추출
                const labels = data.value.map(item => item.bodyPart);
                const values = data.value.map(item => item.cnt);

                const maxCount = Math.max(...values);

                // 차트 생성
                const ctx = document.getElementById('myChart').getContext('2d');
                const myChart = new Chart(ctx, {
                    type: 'polarArea',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: '운동 횟수',
                            data: values,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.7)',
                                'rgba(54, 162, 235, 0.7)',
                                'rgba(255, 206, 86, 0.7)',
                                'rgba(75, 192, 192, 0.7)',
                                'rgba(153, 102, 255, 0.7)',
                                'rgba(255, 159, 64, 0.7)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            fontColor: '#000000',
                            borderWidth: 1,
                        }]
                    },
                    options: {
                        scales: {
                            r: {
                                beginAtZero: true,
                                max: maxCount + 1,
                                fontColor: "#000000",
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                color: "#000000",
                            }
                        }
                    }
                });
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        });

        return { data };
    }
};
</script>
<style scoped>
.chart {
    position: relative;
    width: 350px;
    height: 350px;
    background-color: var(--bg-200);
    transition: 0.2s;
    border-radius: 20px;
    padding: 20px;
}

.chart:hover {
    box-shadow: 0 0 10px var(--text-200)
}

.no {
    position: absolute;
    top: 90px;
    left: 50px;
    font-size: 20px;
    padding: 20px;
}

.inf {
    margin-bottom: 20px;
    text-align: center;
}

.sub-link1 {
    font-size: 50px;
    margin-left: 25px;
    color: var(--text-200);
    padding: 15px;
    background-color: var(--primary-200);
    border-radius: 20px;
}

.sub-link1:hover {
    background-color: var(--primary-100);
}
</style>

