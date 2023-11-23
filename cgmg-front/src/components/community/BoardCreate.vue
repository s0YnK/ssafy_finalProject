<template>
    <div class="container">
        <h2>글 작성</h2>

        <div class="form__div">
            <div class="lain tit">
                <label for="title" class="form__label">제목</label>
                <input type="text" id="title" v-model="board.title" class="form__input input">
            </div>
            <div class="lain">
                <label for="gymAddress" class="form__label">장소 선택</label>
                <input type="text" id="gymAddress" v-model="board.gymAddress" class="form__input input">
            </div>
            <div class="lain">
                <label for="content" class="form__label">내용</label>
                <textarea id="content" cols="30" rows="10" v-model="board.content" class="form__input textarea"></textarea>
            </div>
            <div class="buttonarea">
                <button @click="createBoard" class="search-btn">등록</button>
            </div>
            <div id="map"></div>
            <div>
                <input v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
                <button @click="searchPlaces">검색</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/community";
import { useRoute } from "vue-router";

let map = null;
let infowindow = null;
let ps = null;
const searchKeyword = ref('');
let markers = [];
const store = useBoardStore()
const board = ref({
    writer: JSON.parse(localStorage.getItem("loginUser")).userId,
    title: '',
    content: '',
    gymAddress: '',
})
onMounted(async () => {
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
});

const createBoard = function () {
    store.createBoard(board.value)
}


const initMap = () => {
    const container = document.getElementById('map');
    const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 7,
    };
    map = new kakao.maps.Map(container, options);

    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    ps = new kakao.maps.services.Places(map);

    // 사용자의 현재 위치를 얻어오는 함수 호출
    getCurrentLocation();
};

// 사용자의 현재 위치를 얻어오는 함수
const getCurrentLocation = () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
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

function placesSearchCB(data, status, pagination) {
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
        gymAddress.value = place.address_name;
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}

function removeMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

const searchPlaces = () => {
    removeMarkers();
    ps.keywordSearch(searchKeyword.value, placesSearchCB, { useMapBounds: true });
};

</script>

<style scoped>
.container {
    max-width: 800px;
    margin: 20px auto;
    background-color: #fff;
    border-radius: 8px;
    padding: 20px;
}

.form-group {
    margin-bottom: 20px;
}

.lain {
    display: flex;
}

label {
    width: 40px;
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
    margin-right: 20px;
    font-size: 20px;
}

.input {
    font-size: 16px;
    margin-bottom: 40px;
    width: 90%;
    padding: 10px;
    box-sizing: border-box;
    border: none;
    border-bottom: 1px solid #ccc;
    border-radius: 0;
    transition: border-bottom 0.3s;
}

.tit {
    margin-top: 40px;
}

.textarea {
    width: 90%;
    padding: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 10px;
    margin-bottom: 40px;
}

.input:focus {
    outline: none;
    border-bottom: 1px solid #6ed8b7;
}

.textarea:focus {
    outline: none;
    border: 1px solid #6ed8b7;
}

.buttonarea {
    width: 800px;
    display: flex;
    justify-content: center;
}

.search-btn {
    font-size: 20px;
    color: #fff;
    width: 120px;
    height: 50px;
    border-radius: 10px;
    border: solid 2px rgb(184, 225, 211);
    background-color: rgb(184, 225, 211);
    transition: 0.2s;
    cursor: pointer;
}

.search-btn:hover {
    background-color: rgb(55, 182, 140);
    border: solid 2px rgb(55, 182, 140);
}

.search-btn:click {
    background-color: rgb(184, 225, 211);
}

#map {
    width: 600px;
    height: 600px;
}

input {
    margin-top: 10px;
    padding: 5px;
}

button {
    margin-top: 10px;
    padding: 5px;
}
</style>