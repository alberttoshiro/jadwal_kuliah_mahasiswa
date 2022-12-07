package com.albert.util;

import java.util.HashMap;
import java.util.Map;

public class HariUtil {
  private static Map<String, Integer> hariMapping;
  private static Map<Integer, String> namaHariMapping;

  public static String getNamaHari(Integer kodeHari) {
    if (namaHariMapping == null) {
      initNamaHariMapping();
    }
    if (namaHariMapping.containsKey(kodeHari)) {
      return namaHariMapping.get(kodeHari);
    } else {
      return null;
    }
  }

  public static Integer getOrder(String hari) {
    if (hariMapping == null) {
      initHariMapping();
    }
    if (hariMapping.containsKey(hari)) {
      return hariMapping.get(hari);
    } else {
      return 0;
    }
  }

  private static void initHariMapping() {
    hariMapping = new HashMap<>();
    hariMapping.put("Senin", 1);
    hariMapping.put("Selasa", 2);
    hariMapping.put("Rabu", 3);
    hariMapping.put("Kamis", 4);
    hariMapping.put("Jumat", 5);
    hariMapping.put("Sabtu", 6);
    hariMapping.put("Minggu", 7);
  }

  private static void initNamaHariMapping() {
    namaHariMapping = new HashMap<>();
    namaHariMapping.put(1, "Senin");
    namaHariMapping.put(2, "Selasa");
    namaHariMapping.put(3, "Rabu");
    namaHariMapping.put(4, "Kamis");
    namaHariMapping.put(5, "Jumat");
    namaHariMapping.put(6, "Sabtu");
    namaHariMapping.put(7, "Minggu");
  }
}
