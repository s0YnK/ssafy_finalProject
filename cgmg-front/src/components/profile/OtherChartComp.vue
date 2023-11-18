<template>
    <div style="width: 250px; height: 250px;">
        <canvas id="myChart" width="100" height="100"></canvas>
    </div>
    <div style="width: 250px; height: 250px;">
        <canvas id="myChart2" width="100" height="100"></canvas>
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

                // bodyPart와 cnt 추출
                const labels = data.value.map(item => item.bodyPart);
                const values = data.value.map(item => item.cnt);

                const maxCount = Math.max(...values);

                // 차트 생성
                const ctx = document.getElementById('myChart').getContext('2d');
                const ctx2 = document.getElementById('myChart2').getContext('2d');
                const myChart = new Chart(ctx, {
                    type: 'radar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: '운동 횟수',
                            data: values,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            r: {
                                beginAtZero: true,
                                max: maxCount + 1
                            }
                        }
                    }
                });
                const myChart2 = new Chart(ctx2, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: '운동 횟수',
                            data: values,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            r: {
                                beginAtZero: true,
                                max: maxCount + 1
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

