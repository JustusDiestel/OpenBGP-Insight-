# OpenBGP-Insight – Ein verteiltes System zur Analyse globaler Internet-Routing-Dynamiken

## Testdatei: 

1. test.mrt erstellen: app/test.mrt
2. test datei bekannte größe zuweise: dd if=/dev/zero of=app/test.mrt bs=16 count=5 
3. ausführen: ./gradlew run --args="analyze --mrt test.mrt --out out.json"




## Überblick
OpenBGP-Insight ist ein technisch tiefgehendes Analyse- und Forschungssystem zur Untersuchung des globalen Internet-Routings.  
Das Projekt verarbeitet **Live-BGP-Daten** aus öffentlichen, unbeschränkten Quellen (RIPE RIS, RouteViews), rekonstruiert daraus **AS-Level-Graphen** und erkennt **Anomalien** wie Prefix Hijacks, Route Leaks und großflächige Erreichbarkeitsstörungen.

Ziel ist **nicht** Visualisierung allein, sondern die **modellbasierte Rekonstruktion und Bewertung realer Routing-Ereignisse** im Internet.

---

## Motivation
Das Border Gateway Protocol (BGP) ist das Rückgrat des Internets, gleichzeitig jedoch fehleranfällig und kaum überwacht.  
Routing-Fehler führen regelmäßig zu:
- globalen Ausfällen
- Performance-Einbrüchen
- Sicherheitsvorfällen

OpenBGP-Insight adressiert dieses Problem, indem es rohe Routing-Updates systematisch erfasst, analysiert und korreliert.

---

## Datenquellen (kostenlos & unbeschränkt)
- **RIPE RIS (Live & Dumps)** – BGP Update Streams
- **RouteViews** – globale Routing-Tabellen
- **IANA / RIR Delegation Files** – ASN- und Prefix-Zuordnung
- **Public DNS Resolvers** (optional zur Korrelation)

Es werden **keine proprietären APIs** oder kostenpflichtigen Dienste verwendet.

---

## Systemarchitektur (High-Level)

```text
┌───────────────┐
│ BGP Collectors│  (RIPE RIS / RouteViews)
└──────┬────────┘
       │
       ▼
┌────────────────────────┐
│ Ingestion Layer        │
│ - MRT Parsing          │
│ - Update Normalization │
└──────┬─────────────────┘
       │
       ▼
┌────────────────────────┐
│ Routing Model Core     │
│ - AS-Graph Builder     │
│ - Path History         │
│ - Temporal Index       │
└──────┬─────────────────┘
       │
       ▼
┌────────────────────────┐
│ Analysis Engine        │
│ - Hijack Detection     │
│ - Leak Detection       │
│ - Churn Metrics        │
└──────┬─────────────────┘
       │
       ▼
┌────────────────────────┐
│ Output Layer           │
│ - JSON / CSV           │
│ - Alerts               │
│ - Offline Reports      │
└────────────────────────┘