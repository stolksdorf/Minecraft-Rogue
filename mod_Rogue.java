package net.minecraft.src;

public class mod_Rogue extends BaseMod{
	
	public static ItemPitTrap pitTrapItem;
	public static BlockPitTrap pitTrapBlock;
	
	public static int pitTrapTexture = 34;

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	
	public mod_Rogue(){
	
		setupIds();
		addRecipes();
		
	}
	
	private void setupIds(){
		pitTrapBlock = new BlockPitTrap(204);
		pitTrapBlock.setBlockName("pittrap");
		ModLoader.RegisterBlock(pitTrapBlock);
		ModLoader.AddName(pitTrapBlock, "Pit Trap");
		
		pitTrapItem = new ItemPitTrap(1001);
		pitTrapItem.setItemName("pitTrapItem");
		ModLoader.AddName(pitTrapItem, "Pit Trap");
	}
	
	private void addRecipes(){
		
		ModLoader.AddRecipe(
				new ItemStack(pitTrapItem, 1),
				new Object[]{"Y",
					Character.valueOf('Y'), Block.sandStone
				});
		
	}

}
