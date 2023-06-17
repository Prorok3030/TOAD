function init(){
        let myMap = new ymaps.Map("map_test", {
            center: [56.13417769526106,40.40740513745116],
             zoom: 12

        }, {
            searchControlProvider: 'yandex#search'
        }),
        objectManager = new ymaps.ObjectManager({
            clusterize:true,
            gridSize: 32,
            clusterDisableClickZoom: true
        });

        const xhr = new XMLHttpRequest();

        xhr.open('GET', 'http://localhost:8080/getUserFav');

        xhr.responseType ='json';

        xhr.onload = () =>{
           let objects = xhr.response;
           objects.forEach( mark =>{
           var coords = mark.coords.split(',')
                     objectManager.add(
                       {
                         type:'Feature',
                         id: mark.id,
                         geometry:{
                           type: 'Point',
                           coordinates: coords
                         },
                         properties:{
                           balloonContent: mark.text
                         }
                       }
                     )
                   });
        }

        xhr.send()

                myMap.geoObjects.add(objectManager);
}
ymaps.ready(init);