    function init(){
        var myPlacemark
        let myMap = new ymaps.Map("map_add", {
            // Координаты центра карты.
            // Порядок по умолчанию: «широта, долгота».
            center: [56.13417769526106,40.40740513745116],
            // Уровень масштабирования. Допустимые значения:
            // от 0 (весь мир) до 19.
             zoom: 12

        });

        // Слушаем клик на карте.
    myMap.events.add('click', function (e) {
        var coords = e.get('coords');
        document.getElementById("coords").value = coords;

        // Если метка уже создана – просто передвигаем ее.
        if (myPlacemark) {
            myPlacemark.geometry.setCoordinates(coords);
        }
        // Если нет – создаем.
        else {
            myPlacemark = createPlacemark(coords);
            myMap.geoObjects.add(myPlacemark);
            // Слушаем событие окончания перетаскивания на метке.
            myPlacemark.events.add('dragend', function () {
                getAddress(myPlacemark.geometry.getCoordinates());
            });
        }
        getAddress(coords);
    });

    // Определяем адрес по координатам (обратное геокодирование).
        function getAddress(coords) {
            myPlacemark.properties.set('iconCaption', 'поиск...');
            ymaps.geocode(coords).then(function (res) {
                var firstGeoObject = res.geoObjects.get(0);
                let address = firstGeoObject.getAddressLine();
                document.getElementById("address").value = address;

                myPlacemark.properties
                    .set({
                        // Формируем строку с данными об объекте.
                        iconCaption: [
                            // Название населенного пункта или вышестоящее административно-территориальное образование.
                            firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                            // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                            firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                        ].filter(Boolean).join(', '),
                        // В качестве контента балуна задаем строку с адресом объекта.
                        balloonContent: firstGeoObject.getAddressLine()
                    });
            });
        }

    // Создание метки.
    function createPlacemark(coords) {
        return new ymaps.Placemark(coords, {
            preset: 'islands#violetDotIconWithCaption',
            draggable: true
        });
    }
}
ymaps.ready(init);