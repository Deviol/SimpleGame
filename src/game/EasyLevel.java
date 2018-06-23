package game;

public class EasyLevel extends DefaultLevel{
    private Hero hero;
    private HeroDirection forbiddenDirection;

    public EasyLevel(Hero hero, HeroDirection forbiddenDirection) {
        super(hero);
        this.forbiddenDirection = forbiddenDirection;
    }

    public void movingLeft() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.LEFT) {
            throw new ForbiddenDirectionException();
        }
        super.movingLeft();
    }

    public void movingRight() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.RIGHT) {
            throw new ForbiddenDirectionException();
        }
        super.movingRight();
    }

    public void movingUp() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.UP) {
            throw new ForbiddenDirectionException();
        }
        super.movingUp();
    }

    public void movingDown() throws HeroStepOutOfGameFieldBoundsException, ForbiddenDirectionException {
        if(forbiddenDirection == HeroDirection.DOWN) {
            throw new ForbiddenDirectionException();
        }
        super.movingDown();
    }

    public void activateSpell() throws NoSpellFoundException {
        super.activateSpell();
    }
}
