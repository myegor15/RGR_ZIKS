package com.melnichuk.rgr_ziks.cryptography;

public class EncryptionTable {

    public final String[][] ALPHABET =
            {{"а", "022", "190", "206"}, //а
            {"б", "134", "289", "913"}, //б
            {"в", "416", "543", "580"},//в
            {"г", "394", "618", "845"}, //г
            {"ґ", "253", "284", "942"}, //ґ
            {"д", "162", "450", "984"}, //д
            {"е", "279", "632", "739"}, //е
            {"є", "156", "330", "666"}, //є
            {"ж", "001", "562", "746"}, //ж
            {"з", "288", "513", "570"}, //з
            {"и", "082", "165", "349"}, //и
            {"і", "212", "724", "883"}, //і
            {"ї", "029", "501", "727"}, //ї
            {"й", "509", "553", "613"}, //й
            {"к", "054", "407", "944"}, //к
            {"л", "617", "663", "823"}, //л
            {"м", "511", "940", "989"}, //м
            {"н", "080", "120", "300"}, //н
            {"о", "151", "347", "667"}, //о
            {"п", "199", "375", "757"}, //п
            {"р", "710", "084", "977"}, //р
            {"с", "139", "950", "988"}, //с
            {"т", "235", "802", "970"}, //т
            {"у", "214", "282", "575"}, //у
            {"ф", "075", "277", "499"}, //ф
            {"х", "358", "708", "904"}, //х
            {"ц", "160", "532", "903"}, //ц
            {"ч", "144", "280", "399"}, //ч
            {"ш", "433", "542", "878"}, //ш
            {"щ", "298", "622", "983"}, //щ
            {"ь", "343", "565", "684"}, //ь
            {"ю", "231", "287", "591"}, //ю
            {"я", "204", "266", "588"}, //я
            {"0", "047", "753", "785"}, //0
            {"1", "227", "500", "923"}, //1
            {"2", "734", "897", "914"}, //2
            {"3", "115", "137", "786"}, //3
            {"4", "003", "152", "623"}, //4
            {"5", "039", "050", "857"}, //5
            {"6", "239", "242", "668"}, //6
            {"7", "021", "439", "939"}, //7
            {"8", "043", "009", "934"}, //8
            {"9", "470", "808", "978"}, //9
            {" ", "467", "714", "992"}, //пробел
            {".", "525", "583", "899"}, //.
            {",", "243", "333", "792"}, //,
            {";", "489", "750", "801"}, //;
            {"-", "654", "867", "868"}, //-
            {"!", "302", "614", "796"}, //!
            {"?", "278", "356", "863"}, //?
            {":", "307", "624", "917"}}; //:

    public byte counters[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
}
