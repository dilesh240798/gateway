package com.scb.scroe.gateway.ip88transaction.step1;
import java.util.HashSet;

import com.scb.scroe.gateway.ip88transaction.model.TransactionEntry;
import com.scb.scroe.gateway.repository.DatabaseAccessObject;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;

import com.scb.scroe.gateway.constant.BatchConstant;

public class TransactionDupFilterProcessor implements ItemProcessor <TransactionEntry,TransactionEntry>, ChunkListener{

	private DatabaseAccessObject dao;
    private JobParameters jobParameters;

    private HashSet<TransactionEntry> chunkSet;

    TransactionDupFilterProcessor(DatabaseAccessObject dao,
                                  JobParameters jobParameters,
                                  HashSet<TransactionEntry> chunkSet) {
        this.dao = dao;
        this.chunkSet = chunkSet;
        this.jobParameters = jobParameters;
    }
    
	@Override
	public TransactionEntry process(TransactionEntry entity) throws Exception {
		String jobId = this.jobParameters.getString(BatchConstant.JOB_ID);
        //Optional<TransactionEntry> dupEntity = this.dao.fetchMerchant(entity.getFileName(), entity.getParticipantId(), entity.getAction(), jobId);
//        if (dupEntity.isPresent() || this.chunkSet.contains(entity)) {
//            log.error("Duplicate record found in file exception");
//            entity.setResponse(Response.REJECTED);
//            entity.setRejectReason(MerchantMgmtResponse.INVALID_RECORD);
//            this.chunkSet.add(entity);
//            return entity;
//        }
        this.chunkSet.add(entity);
        return entity;
	}

    @Override
    public void beforeChunk(ChunkContext context) {

    }

    @Override
    public void afterChunk(ChunkContext context) {

    }

    @Override
    public void afterChunkError(ChunkContext context) {

    }
}
