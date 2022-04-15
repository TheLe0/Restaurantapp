package br.com.leotosin.restaurantapp.util;

import br.com.leotosin.restaurantapp.R;

public class ImageViewUtil {

    public static int findImageResourceIdFromImageName(String imageName) {
        switch (imageName) {
            case "carajillo": return R.drawable.carajillo;
            case "cerveja": return R.drawable.cerveja;
            case "coca_normal_lata": return R.drawable.coca_normal_lata;
            case "coca_zero_lata": return R.drawable.coca_zero_lata;
            case "suco_de_laranja": return R.drawable.suco_de_laranja;
            case "croissants": return R.drawable.croissants;
            case "frango_xadrez": return R.drawable.frango_xadrez;
            case "pretzel": return R.drawable.pretzel;
            case "salada_caezar": return R.drawable.salada_caezar;
            case "yakisoba": return R.drawable.yakisoba;
            default: return 0;
        }
    }
}
