package com.scb.scroe.gateway.ip88transaction.step1;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ItemCountListener implements ChunkListener {

	@Override
	public void afterChunk(ChunkContext context) {
		int count = context.getStepContext().getStepExecution().getReadCount();
        System.out.println("ItemCount: " + count);
        count=context.getStepContext().getStepExecution().getSkipCount();
        System.out.println("SkipCount: " + count);
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		
	}

}
