package me.sebastian420.PandaLeadBreak;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PandaLeadBreak implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("panda-lead-break");

	@Override
	public void onInitialize() {
		LOGGER.info("PandaLeadBreak");
	}
}