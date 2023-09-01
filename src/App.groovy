import UI.Menu

class App {
    static void main(String[] args) {
        Menu menu = new Menu()
        Reader reader = new BufferedReader(new InputStreamReader(System.in))
        menu.exibirMenuCandidato(reader)
    }
}
