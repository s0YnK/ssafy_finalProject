<template>
    <div style="width: 300px; height: 300px; background-color: var(--bg-200); transition: 0.2s; border-radius: 20px;">
        <canvas id="myChart" width="100" height="100"></canvas>
    </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';
import axios from 'axios';

export default {
    setup() {
        const data = ref([]);

        onMounted(async () => {
            try {
                const response = await axios.get('http://localhost:8080/exercise-api/exercise/log/part', {
                    params: {
                        userId: JSON.parse(localStorage.getItem("loginUser")).userId
                    }
                });

                data.value = response.data;

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

        return {};
    }
};
</script>

