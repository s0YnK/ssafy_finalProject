import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useBoardStore } from "@/stores/community";

export default (() => {
const __VLS_setup = async () => {
const store = useBoardStore();
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

// // 사용자의 현재 위치를 얻어오는 함수
// const getCurrentLocation = () => {
//     if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition(
//             (position) => {
//                 const lat = position.coords.latitude;
//                 const lng = position.coords.longitude;
//                 const currentLocation = new kakao.maps.LatLng(lat, lng);
//                 // 지도의 중심을 현재 위치로 이동
//                 map.setCenter(currentLocation);
//                 // 장소 검색 서비스 호출 (주변 검색)
//                 ps.categorySearch('PM9', placesSearchCB, { useMapBounds: true });
//             },
//             (error) => {
//                 console.error('Error getting user location:', error);
//             }
//         );
//     } else {
//         console.error('Geolocation is not supported by this browser.');
//     }
// };
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
console.log(place.address_name);
infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
infowindow.open(map, marker);
});
}

const searchPlaces = () => {
console.log(store.board.gymAddress);
ps.keywordSearch(store.board.gymAddress, placesSearchCB);
};

onMounted(async () => {
store.getBoard(route.params.id);
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
searchPlaces();
});
const __VLS_publicComponent = (await import('vue')).defineComponent({
setup() {
return {};
},
});

const __VLS_componentsOption = {};

let __VLS_name!: 'kakaoMapApi';
function __VLS_template() {
let __VLS_ctx!: InstanceType<import('./__VLS_types.js').PickNotAny<typeof __VLS_publicComponent, new () => {}>> & InstanceType<import('./__VLS_types.js').PickNotAny<typeof __VLS_internalComponent, new () => {}>> & {};
/* Components */
let __VLS_localComponents!: NonNullable<typeof __VLS_internalComponent extends { components: infer C; } ? C : {}> & typeof __VLS_componentsOption & typeof __VLS_ctx;
let __VLS_otherComponents!: typeof __VLS_localComponents & import('./__VLS_types.js').GlobalComponents;
let __VLS_own!: import('./__VLS_types.js').SelfComponent<typeof __VLS_name, typeof __VLS_internalComponent & typeof __VLS_publicComponent & (new () => { $slots: typeof __VLS_slots; }) >;
let __VLS_components!: typeof __VLS_otherComponents & Omit<typeof __VLS_own, keyof typeof __VLS_otherComponents>;
/* Style Scoped */
type __VLS_StyleScopedClasses = {};
let __VLS_styleScopedClasses!: __VLS_StyleScopedClasses | keyof __VLS_StyleScopedClasses | (keyof __VLS_StyleScopedClasses)[];
/* CSS variable injection */
/* CSS variable injection end */
let __VLS_templateComponents!: {};
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = {};
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { id: ("map"), };
}
}
if (typeof __VLS_styleScopedClasses === 'object' && !Array.isArray(__VLS_styleScopedClasses)) {
}
declare var __VLS_slots: {};
return __VLS_slots;
}
const __VLS_internalComponent = (await import('vue')).defineComponent({
setup() {
return {};
},
});
return {} as typeof __VLS_publicComponent;
};
return {} as typeof __VLS_setup extends () => Promise<infer T> ? T : never;
})({} as any);
