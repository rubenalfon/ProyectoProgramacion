package com.gf.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;

public class ReverseGeocoding {

    public static String getCountryName(double latitude, double longitude) {
        String countryName = "";
        if (longitude > 180) {
            longitude -= 360;
        } else if (longitude < -180) {
            longitude += 360;
        }
        try {
            // Inicializa el contexto de la API de Google Maps
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyBIxZoOuYHvyLWiiKfoeK29D8unL0Wv02Q") // Reemplaza con tu propia API Key de Google Maps
                    .build();

            // Realiza la geocodificación inversa utilizando las coordenadas
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new com.google.maps.model.LatLng(latitude, longitude)).language("es").resultType(AddressType.COUNTRY).await();

            // Extrae el nombre del país del resultado
            if (results.length > 0) {
                countryName = results[0].formattedAddress;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryName;
    }
}
