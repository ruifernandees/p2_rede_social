public class CommunityContent extends Content {
    @Override
    public void addMessage(Message message) {
        super.addMessage(message);
    }

    @Override
    public void show() {
        if (this.messages.size() == 0) {
            System.out.println("Sem conteúdo!\n");
            return;
        }
        for (int i = 0; i < this.messages.size(); i++) {
          System.out.println(this.messages.get(i));
        }
        System.out.println();
    }
}