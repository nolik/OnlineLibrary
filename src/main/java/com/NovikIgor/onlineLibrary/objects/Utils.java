package com.NovikIgor.onlineLibrary.objects;

import org.springframework.stereotype.Component;

/**
 * Created by nolik on 21.01.17.
 */

@Component
public class Utils {
    private Character[] letters = new Character[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    public Character[] getLetters() {
        return letters;
    }
}
