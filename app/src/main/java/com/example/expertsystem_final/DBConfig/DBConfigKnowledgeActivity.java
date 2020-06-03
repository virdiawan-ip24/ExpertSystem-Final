package com.example.expertsystem_final.DBConfig;

public class DBConfigKnowledgeActivity {
    public static final String URL_ADD = "http://localhost/admin-finalproject/knowledge/create_knowledge.php";
    public static final String URL_GET = "http://localhost/admin-finalproject/knowledge/read_knowledge.php";
    public static final String URL_UPDATE = "http://localhost/admin-finalproject/knowledge/update_knowledge.php";
    public static final String URL_DELETE = "http://localhost/admin-finalproject/knowledge/delete_knowledge.php?id_knowledge=";

    public static final String KEY_ID_KNOWLEDGE = "id_knowledge";
    public static final String KEY_FK_ID_GEJALA = "fk_id_gejala";
    public static final String KEY_PROSENS_KLINIS = "prosens_klinis";
    public static final String KEY_FK_ID_GEJALA_KLINIS = "fk_id_gejala_klinis";
    public static final String KEY_PROSENS_PENYAKIT = "prosens_penyakit";
    public static final String KEY_FK_ID_PENYAKIT = "fk_id_penyakit";
    public static final String KEY_THRESHOLD = "threshold";

    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID_KNOWLEDGE = "id_knowledge";
    public static final String TAG_FK_ID_GEJALA = "fk_id_gejala";
    public static final String TAG_PROSENS_KLINIS = "prosens_klinis";
    public static final String TAG_FK_ID_GEJALA_KLINIS = "fk_id_gejala_klinis";
    public static final String TAG_PROSENS_PENYAKIT = "prosens_penyakit";
    public static final String TAG_FK_ID_PENYAKIT = "fk_id_penyakit";
    public static final String TAG_THRESHOLD = "threshold";

    public static final String ID_KNOWLEDGE = "id";
}
