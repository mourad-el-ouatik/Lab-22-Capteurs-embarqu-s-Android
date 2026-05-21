# ⭐ LAB-22 | Sensors App — Capteurs Android

Une application Android démontrant l'utilisation des **capteurs matériels** d'un appareil Android : température, humidité, proximité, champ magnétique, accéléromètre, gravité, gyroscope, podomètre, boussole et reconnaissance d'activité, avec affichage en temps réel sous forme de graphes.

---

## ✨ Fonctionnalités

- 🌡️ **Température ambiante** — lecture en temps réel
- 💧 **Humidité relative** — lecture en temps réel
- 📡 **Proximité** — détection d'objets proches
- 🧲 **Champ magnétique** — mesure sur 3 axes (norme)
- 📈 **Accéléromètre** — mesure de l'accélération x, y, z incluant la gravité
- 🌍 **Gravité** — composante gravitationnelle uniquement
- 🔄 **Gyroscope** — taux de rotation en rad/s
- 👣 **Podomètre** — comptage des pas
- 🧭 **Boussole** — orientation magnétique
- 🏃 **Reconnaissance d'activité** — détection automatique du mouvement
- 📊 **Graphe en temps réel** — visualisation des valeurs via `LineChartView`
- 🗂️ **Navigation par tiroir** — accès rapide à chaque capteur

---

## 🛠️ Stack technique

| Composant | Technologie |
|---|---|
| Langage | Java |
| UI | XML Layouts + Programmatic Views |
| Navigation | Navigation Component + DrawerLayout |
| Capteurs | Android SensorManager |
| Graphe | Custom `LineChartView` |
| Architecture | Single Activity + Fragments |
| Lifecycle | AndroidX Fragment Lifecycle |

---

## 📁 Architecture du projet

```bash
ensa.ma.sensors
├── MainActivity.java                        # Navigation principale + gestion du tiroir
├── fragments/
│   ├── SensorGraphFragment.java             # Fragment générique (température, humidité, proximité, magnétique)
│   ├── MotionSensorFragment.java            # Fragment générique (accéléromètre, gravité, gyroscope)
│   ├── StepCounterFragment.java             # Podomètre
│   ├── CompassFragment.java                 # Boussole
│   ├── ActivityRecognitionFragment.java     # Reconnaissance d'activité
│   └── SensorsListFragment.java             # Liste des capteurs disponibles
├── views/
│   └── LineChartView.java                   # Vue graphique personnalisée
├── utils/
│   └── SensorFormatter.java                 # Formatage des valeurs capteurs
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── content_main.xml
    │   └── fragment_*.xml
    └── menu/
        ├── activity_main_drawer.xml
        └── main.xml
```

---

## 📦 Dépendances

```gradle
dependencies {
    implementation "androidx.navigation:navigation-fragment:2.7.7"
    implementation "androidx.navigation:navigation-ui:2.7.7"
    implementation "com.google.android.material:material:1.11.0"
    implementation "androidx.drawerlayout:drawerlayout:1.2.0"
}
```

---

## 🧠 Concepts Android abordés

- 📌 `SensorManager` et `SensorEventListener`
- 📌 `Sensor.TYPE_*` — types de capteurs Android
- 📌 Lecture des axes x, y, z via `SensorEvent.values[]`
- 📌 Calcul de la norme vectorielle `sqrt(x² + y² + z²)`
- 📌 Navigation par `DrawerLayout` + `NavigationView`
- 📌 Fragment dynamique avec `FragmentManager.replace()`
- 📌 Gestion du cycle de vie : `onResume` / `onPause`
- 📌 Vue personnalisée (`LineChartView`)
- 📌 Coexistence `NavController` + fragments manuels

---

## 🔄 Fonctionnement

1. L'utilisateur ouvre le tiroir de navigation
2. Il sélectionne un capteur dans le menu
3. Le fragment correspondant est chargé dans `fragment_container`
4. Le `SensorManager` enregistre le listener dans `onResume()`
5. Chaque événement capteur met à jour les valeurs texte et le graphe
6. Le listener est libéré dans `onPause()` pour économiser la batterie

---

## 📱 Interface

- **Tiroir de navigation** listant tous les capteurs disponibles
- **Fragment capteur** avec :
  - Titre du capteur
  - Valeurs numériques en temps réel (x, y, z ou valeur unique)
  - Graphe linéaire animé de la norme ou de la valeur
- **Message** `"Capteur indisponible sur ce dispositif."` si le capteur est absent

---

## 📊 Parties du laboratoire

| Partie | Capteurs |
|---|---|
| Partie 1 | Liste des capteurs disponibles |
| Partie 2 | Température & Humidité |
| Partie 3 | Proximité & Champ magnétique |
| Partie 4 | Boussole |
| Partie 5 | Accéléromètre, Gravité, Gyroscope |
| Partie 6 | Podomètre |
| Partie 7 | Reconnaissance d'activité |

---

## 🎯 Objectif pédagogique

Ce laboratoire montre comment :

- Accéder aux capteurs matériels d'un appareil Android via `SensorManager`
- Distinguer les différents types de capteurs (mouvement, environnement, position)
- Concevoir une architecture **Single Activity / Multi-Fragment** avec navigation par tiroir
- Gérer correctement le **cycle de vie** des listeners pour éviter les fuites mémoire
- Visualiser des données en temps réel avec une vue graphique personnalisée

---

## 👨‍💻 Auteur

**Mourad EL OUATIK** | Réalisé dans le cadre du **Lab 22 Android** | Programmation & Sécurité des Applications Mobile
