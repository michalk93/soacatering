==SOA Catering==

===Opis katalogów===
- webapp/ - główny katalog aplikacji webowej
- catalog-ejb/ - EJB zarządzający katalogiem kategorii i potraw
- order-ejb/ - EJB zarządzający procesem zamówienia
- client-account-ejb/ - EJB zarządzający procesami użytkownika (logowanie, rejestracja, itp.)
- mail-service-ejb/ - EJB zarządzający wysyłaniem maili 
- salary-system/ - EJB symulujący system płatności
- rest-client/ - aplikacja Java SE symujlująca klienta REST'owego
- delivery-system/ - EJB zarządzający dostawami
- src/ - katalog zawierający część wspólną dla modułów (tj. interfejsy i modele). Implementacje interfejsów zamieszczone są w odpowiednich modułach a użycie ich wykorzystanie odbywa się głównie w aplikacji webowej poprzez Dependency Injection EJB dla konkretnych beanów.

