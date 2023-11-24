<template>
    <div>
        <div id="map"></div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/community";
const store = useBoardStore()
const route = useRoute();


let map = null;
let infowindow = null;
let ps = null;
let markers = [];

const initMap = () => {
    const container = document.getElementById('map');
    const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 7,
    };
    map = new kakao.maps.Map(container, options);

    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    ps = new kakao.maps.services.Places(map);

    // // 사용자의 현재 위치를 얻어오는 함수 호출
    // getCurrentLocation();
};

// 사용자의 현재 위치를 얻어오는 함수
const getCurrentLocation = () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                console.log(position)
                const lat = position.coords.latitude;
                const lng = position.coords.longitude;
                const currentLocation = new kakao.maps.LatLng(lat, lng);

                // 지도의 중심을 현재 위치로 이동
                map.setCenter(currentLocation);

                // 장소 검색 서비스 호출 (주변 검색)
                ps.categorySearch('PM9', placesSearchCB, { useMapBounds: true });
            },
            (error) => {
                console.error('Error getting user location:', error);
            }
        );
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
};

function placesSearchCB(data, status) {
    if (status === kakao.maps.services.Status.OK) {
        for (let i = 0; i < data.length; i++) {
            displayMarker(data[i]);
        }
    }
}

function displayMarker(place) {
    const marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
    });

    markers.push(marker);

    kakao.maps.event.addListener(marker, 'click', function () {
        console.log(place.address_name)
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}

const searchPlaces = () => {
    console.log(store.board.gymAddress)
    ps.keywordSearch(store?.board?.gymAddress, function (result, status) {

        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px 0;">${result[0].place_name}</div>`
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
            // searchPlaces()
        }
    });
};

onMounted(async () => {
    store.getBoard();
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else {
        const script = document.createElement('script');
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=734a29260beb719461ba4d444adab62b&libraries=services`;
        document.head.appendChild(script);
        script.onload = () => {
            kakao.maps.load(initMap);
        };
    }
    setTimeout(() => searchPlaces(), 500)
});
</script>


<style scoped>
#map {
    width: 400px;
    height: 600px;
    border-radius: 20px;
    border: solid 2px var(--primary-200);
}
</style>