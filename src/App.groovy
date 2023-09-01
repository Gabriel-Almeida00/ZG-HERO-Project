import UI.CandidatoMenu

class App {
    static void main(String[] args) {
        CandidatoMenu menu = new CandidatoMenu();
        Reader reader = new BufferedReader(new InputStreamReader(System.in))
        menu.exibirMenuCandidato(reader)
    }
}
